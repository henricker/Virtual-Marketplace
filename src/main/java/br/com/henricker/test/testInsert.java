package br.com.henricker.test;

import java.sql.*;

import Connection.ConnectionFactory;
import br.com.henricker.DAO.ProductDAO;
import br.com.henricker.models.Product;

public class testInsert {

    public static void main(String[] args) throws SQLException {

        Product chair = new Product("Monitor", "Red gamer monitor");
        try(Connection connection = ConnectionFactory.getConnection()) {
            connection.setAutoCommit(false); //Controll of transaction, removing auto commit
            new ProductDAO(connection).save(chair);
            connection.commit();
        }
        System.out.println(chair);
    }
}
