package repository;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductCrudOperation implements CrudOperation<Product>{
    private Connection connection;
    public ProductCrudOperation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> allProduct = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                Product product = new Product();
                product.setProductId(resultSet.getObject("id", UUID.class));
                product.setName((String) resultSet.getObject("name"));
                allProduct.add(product);
            }
        }
        return allProduct;
    }

    @Override
    public List<Product> findById(UUID id) throws SQLException {
        List<Product> productById = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE product_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setProductId(resultSet.getObject("id", UUID.class));
                    product.setName((String) resultSet.getObject("name"));
                    productById.add(product);
                }
            }
        }
        return productById;
    }


    @Override
    public Product save(Product toSave) throws SQLException {
        String sql = "INSERT INTO product (id, name) VALUES (?, ?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setObject(1, toSave.getProductId());
            insertStatement.setString(2, toSave.getName());
            insertStatement.executeUpdate();
        }
        return toSave;
    }

    @Override
    public Product update(UUID id, Product toUpdate) throws SQLException {
        String sql = "UPDATE product SET name = ? WHERE id = ?";

        try (PreparedStatement updateSql = connection.prepareStatement(sql)) {
            updateSql.setString(1, toUpdate.getName());
            updateSql.setObject(2, id);
            updateSql.executeUpdate();
        }

        return toUpdate;
    }

    @Override
    public void delete(UUID id) throws SQLException {
        String sql = "DELETE FROM product WHERE product_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
