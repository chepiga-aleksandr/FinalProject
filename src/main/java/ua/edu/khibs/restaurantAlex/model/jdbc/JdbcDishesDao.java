package ua.edu.khibs.restaurantAlex.model.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.khibs.restaurantAlex.model.Dishes;
import ua.edu.khibs.restaurantAlex.model.DishesDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDishesDao implements DishesDao {

    private DataSource dataSource;

    private static Logger LOGGER = LoggerFactory.getLogger(DishesDao.class);

    @Override
    public Dishes addDishes(Dishes dishes) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO dishes (name, weight, price) VALUES (?,?,?)")) {
            statement.setString(1, dishes.getName());
            statement.setInt(2, dishes.getWeight());
            statement.setInt(3, dishes.getPrice());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return dishes;
            } else {
                throw new RuntimeException("Не смог добавить блюдо ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeDishes(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM dishes WHERE name = ?")) {
            statement.setString(1, name);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Dishes findDishesByName(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM DISHES WHERE name =?")) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createdishes(resultSet);
            } else {
                throw new RuntimeException("Cannot findEmployeeByName dishes with " + name);
            }
        } catch (SQLException e) {
            //           LOGGER.error("Exception occurred while to DB", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Dishes> getAll() {
        List<Dishes> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM DISHES");
            while (resultSet.next()) {
                Dishes dishes = createdishes(resultSet);
                result.add(dishes);
            }
        } catch (SQLException e) {
//            LOGGER.error("Exception occurred while to DB", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    private Dishes createdishes(ResultSet resultSet) throws SQLException {
        Dishes dishes = new Dishes();
        dishes.setName(resultSet.getString("NAME"));
        dishes.setWeight(resultSet.getInt("WEIGHT"));
        dishes.setPrice(resultSet.getInt("PRICE"));
        return dishes;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
