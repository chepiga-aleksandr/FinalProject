package ua.edu.khibs.restaurantAlex.model.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.khibs.restaurantAlex.model.Dishes;
import ua.edu.khibs.restaurantAlex.model.DishesDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDishesDao implements DishesDao {

    private static Logger LOGGER = LoggerFactory.getLogger(DishesDao.class);
    private String url = "jdbc:postgresql://localhost:5432/restaurant";
    private String user = "user";
    private String password = "123456";

    public JdbcDishesDao() {
        loadDriver();
    }

    @Override public Dishes load (int id){
        Dishes dishes = null;
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM DISHES WHERE ID=?")) {
            statement.setInt(1,id);
ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return createdishes(resultSet);
                }else {
                   throw new RuntimeException("Cannot find dishes with " + id);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while to DB" + url, e);
            throw new RuntimeException(e);
        }
    }

        @Override public List<Dishes> getAll () {

            List<Dishes> result = new ArrayList<>();

            try (Connection connection = DriverManager.getConnection(url, user, password);
                 Statement statement = connection.createStatement()) {

                ResultSet resultSet = statement.executeQuery("SELECT * FROM DISHES");

                while (resultSet.next()) {
                    Dishes dishes = createdishes(resultSet);
                    result.add(dishes);
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while to DB" + url, e);
                throw new RuntimeException(e);
            }
            return result;
        }

    private Dishes createdishes(ResultSet resultSet) throws SQLException {
        Dishes dishes = new Dishes();
        dishes.setId(resultSet.getInt("ID"));
        dishes.setName(resultSet.getString("NAME"));
        dishes.setWeight(resultSet.getInt("WEIGHT"));
        dishes.setPrice(resultSet.getInt("PRICE"));
        return dishes;
    }

    private void loadDriver() {
        try {
            LOGGER.info("Loading JDBC driver org.postgresql.Driver ");

            Class.forName("org.postgresql.Driver");

            LOGGER.info("Driver loaded successfully");

        } catch (ClassNotFoundException e) {
            LOGGER.error("Cannot find driver org.postgresql.Driver");
            throw new RuntimeException(e);
        }
    }
}
