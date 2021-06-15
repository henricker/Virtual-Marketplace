package br.com.henricker.test;

import Connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestPoolConnection {
    public static void main(String[] args) throws SQLException {

        for(int i = 1; i <= 20; i++) {
            Connection connection = ConnectionFactory.getConnection();
            System.out.println("Conexão número: " + i);
        }


    }

}
