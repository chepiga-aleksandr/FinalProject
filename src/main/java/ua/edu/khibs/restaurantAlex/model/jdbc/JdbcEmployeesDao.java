package ua.edu.khibs.restaurantAlex.model.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.khibs.restaurantAlex.model.Employees;
import ua.edu.khibs.restaurantAlex.model.EmployeesDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcEmployeesDao implements EmployeesDao {

    private DataSource dataSource;

    private static Logger LOGGER = LoggerFactory.getLogger(Employees.class);

    @Override
    public Employees addEmployee(String name) {

        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO employees (id, name) VALUES (?,?)")) {

            int id = getMaxId() + 1;
            statement.setInt(1, id);
            statement.setString(2, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return createEmployee(resultSet);
            } else {
                throw new RuntimeException("Не смог добавить пользователя " + name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeEmployee(String name) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM employees WHERE name = ?")) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e1) {
            throw new RuntimeException("Не удалось удалить чувака !!!!!!!!! ");
        }
    }

    @Override
    public Employees findEmployeeByName(String name) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE name = ?")) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createEmployee(resultSet);
            } else {
                throw new RuntimeException("Такого персонажа нет в системе!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employees> getAll() {

        List<Employees> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLOYEES");

            while (resultSet.next()) {
                Employees employees = createEmployee(resultSet);
                result.add(employees);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while to DB", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    private Employees createEmployee(ResultSet resultSet) throws SQLException {
        Employees employees = new Employees();
        employees.setId(resultSet.getInt("ID"));
        employees.setName(resultSet.getString("NAME"));
        return employees;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int getMaxId() {

        int maxID = 0;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM employees");

            if (resultSet.next()) {
                maxID = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
        return maxID;
    }
}