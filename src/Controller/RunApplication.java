package Controller;

import DatabaseHandler.School;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RunApplication {
    public static final String hostName = "jdbc:mysql://localhost:3306/school";
    public static final String username = "root";
    public static final String password = "root";
    public static final Scanner userInput = new Scanner(System.in);
    public static boolean running = true;
    School school = new School();


    public void run() throws SQLException {
        Connection connection = DriverManager.getConnection(hostName, username, password);
        Statement statement = connection.createStatement();
        while (running) {
            school.menuList();
            switch (userInput.nextInt()) {
                case 1 -> school.createTable(statement);
                case 2 -> school.addToTable(statement);
                case 3 -> school.viewTableNames(connection);
                case 4 -> school.viewTableContent(statement);
                case 5 -> school.updateRecord(statement);
                case 6 -> school.deleteRecord(statement);
                case 7 -> school.exitConnection(statement, connection);
                default -> {
                    if (running) {
                        System.out.println("Enter a valid number");
                    }
                }
            }
        }
    }
}
