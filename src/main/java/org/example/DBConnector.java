package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.*;
import java.util.List;
import java.util.Objects;

public class DBConnector {
    private static Connection connection;
    public static final String URL = "jdbc:mysql://localhost:3306/NewDB?serverTimezone=CET";
    public static final String USER_NAME = "jdbc_user";
    public static final String PASSWORD = "1234567890";

    public static Connection getConnection() throws SQLException {
        if (Objects.isNull(connection)) {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        }
        return connection;
    }

    public static void showCustomerFile() throws SQLException, IOException {

        Path pathToFile = Paths.get("/Users/sergey.mikitko/Documents/JDBC1_2/src/main/java/org/example/Queries");

        List<String> queries = Files.readAllLines(pathToFile);

        queries.stream().filter(q -> !q.isEmpty()).forEach(query -> {

            if (query.toUpperCase().startsWith("SELECT")) {
                try (Statement statement = getConnection().createStatement();
                     ResultSet resultSet = statement.executeQuery(query)) {

                    while (resultSet.next()) {
                        int customerId = resultSet.getInt("CustomerId");
                        String firstName = resultSet.getString("FirstName");
                        String lastName = resultSet.getString("LastName");
                        String city = resultSet. getString("city");
                        int phone = resultSet.getInt("phone");

                        System.out.println(customerId  + "\t" + firstName + "\t" + lastName + "\t" +
                                "\t" + city + "\t" + phone);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try (Statement statement = getConnection().createStatement()) {

                    statement.execute(query);
                    System.out.println("Query executed");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}