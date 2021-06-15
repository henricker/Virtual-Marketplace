package br.com.henricker.controllers;

import br.com.henricker.DAO.CategoryDAO;
import br.com.henricker.models.Category;
import Connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoryController {

    private Connection connection;
    private CategoryDAO categoryDAO;

    public CategoryController() {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.categoryDAO = new CategoryDAO(connection);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Category> list() {
        try {
            List<Category> categories = this.categoryDAO.list();
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
