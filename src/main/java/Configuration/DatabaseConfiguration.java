package Configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DatabaseConfiguration {
    @Bean
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                System.getenv("JDBC_DATABASE_URL"), System.getenv("JDBC_DATABASE_USERNAME"), System.getenv("JDBC_DATABSE_PASSWORD")
        );
    }
}