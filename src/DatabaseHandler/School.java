package DatabaseHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class School {
    public static boolean running = true;
    public static final Scanner userInput = new Scanner(System.in);

    public void menuList() {
        System.out.println("\n\nEnter:\n***************************************************\n\t" +
                "1 - to create a new table\n\t" +
                "2 - to add to the table\n\t" +
                "3 - to view table names\n\t" +
                "4 - to view table contents\n\t" +
                "5 - to update table records\n\t" +
                "6 - to delete table records\n\t"+
                "7 - to exit the application and close the connection to the database"+
                "\n***************************************************\n\nEnter your choice:");
    }

    public void createTable(Statement statement) {
        try {
            System.out.println("Add a new table ");
            statement.execute("CREATE TABLE IF NOT EXISTS " + userInput.next() + "" + " " +
                    "(name TEXT, lastName TEXT, age INTEGER, phone INTEGER, grades TEXT)");
        } catch (
                SQLException e) {
            System.out.println("No such table in the database 🥲🥲.. Try again\n" + e.getMessage() + "\n");
        }
    }

    public static void addToTable(Statement statement) {
        try {
            System.out.println("Add a new person - type in table name first.");
            statement.execute("INSERT INTO " + userInput.next() + "" + "(name,lastName,age, phone, grades)" + "" +
                    "VALUES('" + userInput.next() + "','" + userInput.next() + "','" + userInput.nextInt() + "','" + userInput.nextInt() + "','" + userInput.next() + "')");
        } catch (SQLException e) {
            System.out.println("No such table in the database 🥲🥲.. Try again\n" + e.getMessage() + "\n");
        }
    }
    public static void viewTableNames(Connection connection) throws SQLException {
        ResultSet resultSet = connection.getMetaData().getTables(null, null, null, null);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("TABLE_NAME"));
        }
    }
    public static void viewTableContent(Statement statement) {
        try {
            statement.execute("SELECT * FROM " + userInput.next());
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getString(1) + " " +
                                resultSet.getString(2)+" " +
                                resultSet.getInt(2) + " " +
                                resultSet.getInt(3) + " " +
                                resultSet.getString(4)+"\n");
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("No such table in the database 🥲🥲.. Try again\n" + e.getMessage() + "\n");
        }
    }
    public static void updateRecord(Statement statement) {
        try {
            System.out.println("Type in the table name, a new name, phonenumber, gmail and the person you want to change.");
            statement.execute("UPDATE '" + userInput.next() + "' SET name='" + userInput.next() + "' , "
                    + "phone='" + userInput.nextInt() + "' , "
                    + "email ='" + userInput.next() + "' "
                    + "WHERE name ='" + userInput.next() + "'");
        } catch (SQLException e) {
            System.out.println("No such table in the database 🥲🥲.. Try again\n" + e.getMessage() + "\n");
        }
    }

    public static void deleteRecord(Statement statement) {
        try {
            System.out.println("To delete a person from the database, type in the table name and then the name of the person.");
            statement.execute("DELETE FROM '" + userInput.next() + "' WHERE name='" + userInput.next() + "'");
        } catch (SQLException e) {
            System.out.println("No such table in the database 🥲🥲.. Try again\n" + e.getMessage() + "\n");
        }
    }

    public void exitConnection(Statement statement, Connection connection) throws SQLException {
        running = false;
        statement.close();
        connection.close();
    }

}
