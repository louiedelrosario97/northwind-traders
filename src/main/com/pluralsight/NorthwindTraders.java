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
        String sql = "SELECT ProductName, UnitPrice FROM products";

        // Try-with-resources
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement= connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery())
        {
            while (resultSet.next())
            {
                System.out.println(resultSet.getString("ProductName"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }
}

