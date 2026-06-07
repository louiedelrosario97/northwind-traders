package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class NorthwindTraders
{
    public static void main(String[] args)
    {
        // DataSource Setup
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername("root");
        dataSource.setPassword("yearup26");

        Scanner scanner = new Scanner(System.in);

        IO.println("What do you want to do?");
        IO.println("1) Display all products");
        IO.println("2) Display all customers");
        IO.println("0) Exit");
        IO.println("Select an option: \n");

        int select = scanner.nextInt();
        switch (select)
        {
            case 1: displayProducts(); break;
            case 2: displayCustomers(); break;
            case 0: System.exit(0);
            default: IO.println("Closing application... (beep!)");
        }
    }

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

                System.out.printf("%-4d %-30s %7.2f %5d%n", id, name, price, stock);
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }
}

