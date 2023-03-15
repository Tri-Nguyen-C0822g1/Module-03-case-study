package dao;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
    void insertProduct(Product product) throws SQLException;

    Product selectProduct(int id);

    List<Product> selectAllProduct();

    boolean deleteProduct(int id) throws SQLException;

    boolean updateProduct(Product product) throws SQLException;

    List<Product> searchByName(String name);

    void addToCart(Product product) throws SQLException;

    List<Product> selectProductFromCart();
//    Product getProductById (int id);


}
