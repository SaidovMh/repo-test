package com.geekbrains.server;

import java.sql.*;

public class SqlDB implements AutoCloseable {

    private static SqlDB instance;
    private static Connection connection;

    private static PreparedStatement findLoginAndPassword;
    private static PreparedStatement changeNick;

    private SqlDB() { }

    public static SqlDB getInstance() {
        if (instance == null) {
            loadDriverAndOpenConnection();
            createPreparedStatement();

            instance = new SqlDB();
        }

        return instance;
    }

    private static void loadDriverAndOpenConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc.sqlite:ChatDB.db");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Ошибка открытия соединения с базой данных!");
            e.printStackTrace();
        }

    }

    private static void createPreparedStatement() {
        try {
            findLoginAndPassword = connection.prepareStatement("SELECT * FROM participant WHERE LOWER(login) = LOWER(?) AND password=?");
            changeNick = connection.prepareStatement("UPDATE participant SET nickname=? WERE nickname=?");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String findLoginAndPassword(String login, String password) {
        ResultSet resultSet = null;

        try {
            findLoginAndPassword.setString(1, login);
            findLoginAndPassword.setString(2, password);

            resultSet = findLoginAndPassword.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("nickname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
        }

        return null;
    }

    private void closeResultSet(ResultSet resultSet) {
            if (resultSet != null) {
            try {
             resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int updateNickname(String oldNickname, String newNickname) {
            try {
                changeNick.setString(1, newNickname);
                changeNick.setString(2, oldNickname);

                return changeNick.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
    }

    @Override
    public void close() {
        try {
            findLoginAndPassword.close();
            changeNick.close();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

