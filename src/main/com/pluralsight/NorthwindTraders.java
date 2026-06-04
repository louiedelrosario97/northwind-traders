package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

public class NorthwindTraders
{
    public static void main(String[] args)
    {
        // DataSource Setup
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername("username");
        dataSource.setPassword("password");

        // Query the Database
        String sql = "SELECT ProductName, UnitPrice FROM products";

    }





}

