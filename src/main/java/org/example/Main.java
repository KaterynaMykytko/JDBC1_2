package org.example;

import java.io.IOException;
import java.sql.SQLException;

import static org.example.DBConnector.showCustomerFile;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {

        showCustomerFile();
    }
}


