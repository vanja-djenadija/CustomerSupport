package com.example.customersupport.dao;

import com.example.customersupport.beans.AdminBean;
import com.example.customersupport.beans.enums.AdminRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    private static final String SELECT_ADMIN = "SELECT * FROM admin WHERE role='SUPPORT' and username=? and password=?";

    private AdminDAO() {
    }

    public static AdminBean selectAdmin(String username, String password) {
        AdminBean admin = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.checkOut();
            PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, SELECT_ADMIN, false);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                admin = new AdminBean(resultSet.getInt("id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("username"), resultSet.getString("password"), AdminRole.SUPPORT);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return admin;
    }

}