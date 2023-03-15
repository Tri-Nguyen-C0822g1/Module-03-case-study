package dao;

import model.Product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class ProductDAO implements IProductDAO{



    private static String jdbcURL = "jdbc:mysql://localhost:3306/casestudy";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "admin";
    private static final String INSERT_PRODUCT = "insert into products( name, description, price, image) values(?,?,?,?);";
    private static final String INSERT_PRODUCT_TO_CART = "insert into cart( name, description, price, image) values(?,?,?,?);";



//    private static final String SELECT_PRODUCT_BY_ID = "select id, name, description, price, image from products where id = ? ;";
    private static final String SELECT_PRODUCT_BY_NAME = "select * from products where name like '%' ? '%';";
    private static final String SELECT_PRODUCT_BY_ID = "select * from products where id = ?;";
//    private static final String SORT_BY_NAME = "select * from products order by name;";
    private static final String SELECT_ALL_PRODUCTS = "select * from products";
    private static final String SELECT_ALL_FROM_CART = "select * from cart";
    private static final String DELETE_PRODUCT_SQL = "delete from products where id = ?; ";
    private static final String UPDATE_PRODUCTS_SQL = "update products set name = ?, description = ?, price = ?, image = ? where id = ?;";

    protected static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            //TODO auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //TODO auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }




    private static void printSQLExeption(SQLException ex) {

        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    @Override
    public Product selectProduct(int id) {
        Product product = null;
        // step 1: Establishing a Connection;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                Double price = rs.getDouble("price");
                String image = rs.getString("image");
                product = new Product(id, name, description, price, image);
            }
        } catch (SQLException e) {
            printSQLExeption(e);
        }
        return product;
    }

    @Override
    public  List<Product> selectAllProduct() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) {

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                products.add(new Product(id, name, description, price, image));
            }
        } catch (SQLException e) {
            printSQLExeption(e);
        }
        return products;
    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTS_SQL);) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getImage());
            statement.setInt(5, product.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public List<Product> searchByName(String name) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_NAME)) {

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id1 = resultSet.getInt("id");
                String name1 = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                String image = resultSet.getString("image");
                Product product= new Product(id1, name1, description, price, image);
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void addToCart(Product product) throws SQLException {

        try(Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_TO_CART);){
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setString(4, product.getImage());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLExeption(e);
        }
    }

    @Override
    public List<Product> selectProductFromCart() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_CART);) {

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                products.add(new Product(id, name, description, price, image));
            }
        } catch (SQLException e) {
            printSQLExeption(e);
        }
        return products;

    }


    @Override
    public void insertProduct(Product product) throws SQLException {
        System.out.println(INSERT_PRODUCT);
        //try-with-resource statement will auto close the connection
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setString(4, product.getImage());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLExeption(e);
        }
    }
}
