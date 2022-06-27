package de.prog2.dungeontop.control.network;

import javafx.application.Platform;

import java.sql.*;

public class MySQLConnector {

    private Statement statement;

    public MySQLConnector(){
        try {
            Connection connection = DriverManager.getConnection("");
            statement = connection.createStatement();
            statement.execute("CREATE TABLE game_lobby(ip_1 INT NOT NULL, ip_2 INT NOT NULL, PRIMARY KEY(ip_1));");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createLobby(String host) {
        try {
            statement.execute("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void joinLobby(String host){
        try {
            statement.execute("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getIPHost(){
        try {
            ResultSet resultSet = statement.executeQuery("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String getIPClient(){
        try {
            ResultSet resultSet = statement.executeQuery("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void clearDB(){
        //TODO on Platform.exit();
        try {
            statement.execute("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
