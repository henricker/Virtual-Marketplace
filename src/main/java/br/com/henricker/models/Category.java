package br.com.henricker.models;
import java.util.ArrayList;
import java.util.List;


public class Category {

    private Integer id;
    private String name;

    private List<Product> products = new ArrayList<>();

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return this.products = products;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
