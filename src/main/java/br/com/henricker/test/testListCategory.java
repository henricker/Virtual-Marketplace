package br.com.henricker.test;

import br.com.henricker.DAO.CategoryDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Connection.ConnectionFactory;
import br.com.henricker.models.Category;

public class testListCategory {

    public static void main(String[] args) throws SQLException {
        try(Connection connection = ConnectionFactory.getConnection()) {
            List<Category> categories =  (new CategoryDAO(connection).listWithProducts());
            for(Category category: categories) {
                System.out.println("Category: " + category.getName());
                System.out.println(category.getProducts());
                System.out.println("-------------------------------");
            }
        }
    }

}
