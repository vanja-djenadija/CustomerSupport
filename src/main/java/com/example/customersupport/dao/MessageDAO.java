package com.example.customersupport.dao;

import com.example.customersupport.beans.MessageBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    private static final ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    private static final String SELECT_ALL = "SELECT m.id, content, is_read, email FROM webshop.message m INNER JOIN user u WHERE m.user_id=u.id;";
    private static final String SELECT_BY_ID = "SELECT m.id, content, is_read, email FROM webshop.message m INNER JOIN user u WHERE m.user_id=u.id AND m.id=?";
    private static final String UPDATE_IS_READ = "UPDATE message m SET is_read=? WHERE m.id=?";

    private MessageDAO() {

    }

    public static List<MessageBean> getAll() {
        Connection connection = null;
        ResultSet resultSet = null;
        List<MessageBean> messages = new ArrayList<>();

        try {
            connection = connectionPool.checkOut();
            PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                messages.add(new MessageBean(resultSet.getInt("id"), resultSet.getString("content"), resultSet.getBoolean("is_read"), resultSet.getString("email")));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return messages;
    }

    public static MessageBean getById(int id) {
        Connection connection = null;
        ResultSet resultSet = null;
        List<MessageBean> messages = new ArrayList<>();

        try {
            connection = connectionPool.checkOut();
            PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, SELECT_BY_ID, false);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                messages.add(new MessageBean(resultSet.getInt("id"), resultSet.getString("content"), resultSet.getBoolean("is_read"), resultSet.getString("email")));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return messages.get(0);
    }

    public static void changeMessageStatus(Integer messageId, boolean isRead) {
        Connection connection = null;

        try {
            connection = connectionPool.checkOut();
            PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, UPDATE_IS_READ, false);
            preparedStatement.setBoolean(1, isRead);
            preparedStatement.setInt(2, messageId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
    }
}