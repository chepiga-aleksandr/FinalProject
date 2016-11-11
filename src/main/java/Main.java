import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        loadDriver();
        LOGGER.info("Connection to DB");
        String url = "jdbc:postgresql://localhost:5432/restaurant";
        String user = "user";
        String password = "123456";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            String sql = "SELECT * FROM DISHES";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Dishes dishes = new Dishes();

                dishes.setId(resultSet.getInt("ID"));
                dishes.setName(resultSet.getString("NAME"));
                dishes.setWeight(resultSet.getInt("WEIGHT"));
                dishes.setPrice(resultSet.getInt("PRICE"));

                System.out.println(dishes.toString());
            }

            LOGGER.info("Successfully connect to DB");
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while to DB" + url, e);
        }

    }

    private static void loadDriver() {
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
