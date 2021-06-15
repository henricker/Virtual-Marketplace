package br.com.henricker.models;

public class Product {

    private Integer id;
    private String name;
    private String description;
    private Integer categoryId;

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Product(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setCategoryId(Integer id) {
        this.categoryId = id;
    }
}
