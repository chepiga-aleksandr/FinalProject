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
    public Employees add() {
        return null;
    }

    @Override
    public Employees remove() {
        return null;
    }

    @Override
    public Employees find() {
        return null;
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
}