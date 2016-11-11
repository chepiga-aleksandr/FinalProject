import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        loadDriver();
        LOGGER.info("Connection to DB");
        String url = "jdbc:postgresql://localhost:5432/restaurant";
        String user = "user";
        String password = "123456";
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            LOGGER.info("Successfully connect to DB");
        } catch (SQLException e) {
            LOGGER.error("Exeption occured while to DB" + url, e);
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
