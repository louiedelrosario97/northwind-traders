package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class NorthwindTraders
{
    static BasicDataSource dataSource = new BasicDataSource();// <--- moved to class level so all methods have access.

    public static void main(String[] args)
    {
        // DataSource Setup
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername("root");
        dataSource.setPassword("yearup26");

        Scanner scanner = new Scanner(System.in);

        IO.println("What do you want to do?");
        IO.println("1) Display all products");
        IO.println("2) Display all customers");
        IO.println("0) Exit");
        IO.print("Select an option: ");

        int select = scanner.nextInt();

        switch (select)
        {
            case 1: displayProducts(); break;
            case 2: displayCustomers(); break;
            case 0: System.exit(0);
            default: IO.println("Closing application... (beep!)");
        }
    }
// ------------------------------------ [ Case 1: displayProducts() ] ---------------------------------------------------

    public static void displayProducts()
    {
        // Query the Database
        String sql = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM products";
        // Try-with-resources
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement= connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery())
        {
            while (resultSet.next())
            {
                int id = resultSet.getInt("ProductID");
                String name = resultSet.getString("ProductName");
                double price = resultSet.getDouble("UnitPrice");
                int stock = resultSet.getInt("UnitsInStock");

                System.out.printf("\n%-4d %-30s %7.2f %5d%n", id, name, price, stock);
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

// ------------------------------------ [ Case 2: displayCustomers() ] --------------------------------------------------
    public static void displayCustomers()
    {
        IO.println();

        // Query the Database
        String sql = "SELECT ContactName, CompanyName, City, Country, Phone FROM customers ORDER BY Country";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement= connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery())
        {
            IO.printf("")
            while (resultSet.next())
            {
                String contactName = resultSet.getString("ContactName");
                String companyName = resultSet.getString("CompanyName");
                String city = resultSet.getString("City");
                String country = resultSet.getString("Country");
                String phone = resultSet.getString("Phone");
                System.out.printf("%-23s %-36s %-17s %-12s %s%n", contactName, companyName, city, country, phone);
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }
}

