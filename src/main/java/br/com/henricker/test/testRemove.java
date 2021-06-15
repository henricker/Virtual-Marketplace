package br.com.henricker.test;

import Connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class testRemove {


    public static void main(String[] args) throws SQLException {
        Integer id = 1;
        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement statement = connection.prepareStatement("DELETE FROM product WHERE id = ?");
        statement.setInt(1, id);
        statement.execute();

        Integer rowsChanges = statement.getUpdateCount();

        System.out.println("Rows changes: " + rowsChanges);

        statement.close();
        connection.close();
    }

}
