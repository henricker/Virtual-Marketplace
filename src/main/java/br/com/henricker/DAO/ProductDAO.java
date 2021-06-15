package br.com.henricker.DAO;

import br.com.henricker.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Pattern -> "Data access Object" used to persistence of data
public class ProductDAO {

    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Product product) throws SQLException {
        String sql = "INSERT INTO product (name, description) VALUES(?,?)";
        try(PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.execute();

            try(ResultSet solve = ps.getGeneratedKeys()) {
                while(solve.next()) {
                    product.setId(solve.getInt(1));
                }
            }
        }
    }

    public List<Product> list() throws SQLException {
        String sql = "SELECT * FROM product";
        List<Product> listProduct = new ArrayList<>();

        try(PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.execute();

            try(ResultSet solve = ps.getResultSet()) {
                while (solve.next()) {
                    Integer id = solve.getInt("id");
                    String name = solve.getString("name");
                    String description = solve.getString("description");

                    listProduct.add(new Product(id, name, description));
                }
            }
        }
        return listProduct;
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM product WHERE id = ?";

        try(PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            ps.execute();

            try(ResultSet rs = ps.getGeneratedKeys()) {
                while(rs.next()) {
                    System.out.println("Product deleted with id: " + id);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String name, String description, Integer id) {
        String sql = "UPDATE product SET name = ?, description = ? WHERE id = ?";

        try(PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, id);
            ps.execute();

            try(ResultSet rs = ps.getGeneratedKeys()) {
                while(rs.next()) {
                    System.out.println("Updated product with id: " + id);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }
}

/*
* But bro, what that difference between DAO and repository ?
*
* repository try follow the business rules of application;
* DAO just registered of data with not business rules
*
* Example:
*
* class ProvaRepository {
     public List<Student> StudentsMeasured();
     public List<Professor> AllTeacher();

     public List<Exame> Allexames();
     public List<Exame> ExamesNotReviewed();
}
*
*
* class ExameDAO {
   public List<DTOExame> TodasProvas();
   public List<DTOExame> ProvasNaoRevisada();
}
class PersonDAO {
   public List<DTOPerson> AlunosAvaliados();
   public List<DTOPerson> TodosProfessores();
}
* */
