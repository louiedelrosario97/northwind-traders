package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NorthwindTraders
{
    public static void main(String[] args)
    {
        // DataSource Setup
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername("root");
        dataSource.setPassword("yearup26");

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

