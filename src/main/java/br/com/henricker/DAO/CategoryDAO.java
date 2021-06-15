package br.com.henricker.DAO;

import br.com.henricker.models.Category;
import br.com.henricker.models.Product;
import com.sun.source.tree.Tree;

import java.sql.*;
import java.util.*;

public class CategoryDAO {

    private Connection connection;

    public CategoryDAO(Connection connection) {
        this.connection = connection;
    }

    public void add(Category category) throws SQLException {
        String sql = "INSERT INTO category (name) VALUES(?)";
        try (PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, category.getName());
            ps.execute();

            try (ResultSet solve = ps.getGeneratedKeys()) {
                while (solve.next()) {
                    category.setId(solve.getInt(1));
                }
            }
        }
    }

    public List<Category> list() throws SQLException {
        String sql = "SELECT * FROM category";
        List<Category> listProduct = new ArrayList<>();

        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.execute();

            try (ResultSet solve = ps.getResultSet()) {
                while (solve.next()) {
                    Integer id = solve.getInt("id");
                    String name = solve.getString("name");

                    listProduct.add(new Category(id, name));
                }
            }
        }
        return listProduct;
    }

    public List<Category> listWithProducts() throws SQLException {
        Map<Integer, Category> categories = new TreeMap();

        String sql =
                "SELECT category.id, category.name, product.id, product.name, product.description " +
                        "FROM category INNER JOIN product ON product.category_id = category.id;";

        try(PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.execute();

            try(ResultSet solve = ps.getResultSet()) {
                while(solve.next()) {
                    if(!categories.containsKey(solve.getInt(1)))
                        categories.put(solve.getInt(1), new Category(solve.getInt(1), solve.getString(2)));
                    categories.get(solve.getInt(1)).addProduct(new Product(solve.getInt(3), solve.getString(4), solve.getString(5)));

                }
            }
        }
        return Collections.unmodifiableList(new ArrayList<Category>(categories.values()));
    }
}
