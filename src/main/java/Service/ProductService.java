package Service;


import model.Product;
import repository.ProductCrudOperation;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ProductService {
    private ProductCrudOperation productCrudOperation;

    public ProductService(ProductCrudOperation productCrudOperation) {
        this.productCrudOperation = productCrudOperation;
    }

    public List<Product> getAllProduct() throws SQLException {
        return productCrudOperation.findAll();
    }
    public Product createProduct(Product toSave) throws SQLException {
        return productCrudOperation.save(toSave);
    }
    public Product updateProduct(UUID id, Product toUpdate) throws SQLException {
        return productCrudOperation.update(id, toUpdate);
    }
    public void deleteProduct(UUID id) throws SQLException {
        productCrudOperation.delete(id);
    }
    public List<Product> getProductById(UUID id) throws SQLException {
        return productCrudOperation.findById(id);
    }
}
