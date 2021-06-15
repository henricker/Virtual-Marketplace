package br.com.henricker.test;

import Connection.ConnectionFactory;
import br.com.henricker.DAO.ProductDAO;

import java.sql.*;

public class testList {

    public static void main(String[] args) throws SQLException {
        try(Connection connection = ConnectionFactory.getConnection()) {
            System.out.println(new ProductDAO(connection).list());
        }
    }
}
