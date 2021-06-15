package br.com.henricker.controllers;

import br.com.henricker.DAO.ProductDAO;
import br.com.henricker.models.Product;
import Connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductController {

    private ProductDAO productDAO;
    private Connection connection;

    public ProductController() {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.productDAO = new ProductDAO(connection);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(Product product)  {
        try {
        this.productDAO.save(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> list()  {
        try {
            List<Product> listProducts = new ArrayList<Product>(this.productDAO.list());
            return listProducts;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(String name, String description, Integer id) {
        this.productDAO.update(name, description, id);
    }

    public void delete(Integer id) {
        this.productDAO.delete(id);
    }
}
