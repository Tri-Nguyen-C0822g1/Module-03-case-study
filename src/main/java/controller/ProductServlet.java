package controller;

import dao.ProductDAO;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
//        String action = request.getServletPath();
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "home":
                backHome(request, response);
                break;
            case "out":
                logout(request, response);
                break;
            case "create":
                newProduct(request, response);
                break;
            case "search":
                searchProduct(request, response);
                break;
            case "init":
                listProduct(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                showDeleteForm(request, response);
                break;
            case "showCart":
                showCart(request, response);
                break;
            case "":
                searchProductAdmin(request, response);
                break;
        }
    }

    private void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productDAO.selectProductFromCart();
        request.setAttribute("products", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/cart.jsp");
        requestDispatcher.forward(request, response);
    }

    private void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String name = request.getParameter("name");
        String description = request.getParameter("desc");
        double price = Double.parseDouble(request.getParameter("price"));
        String image = request.getParameter("image");
        Product product = new Product(name, description, price, image);
        productDAO.addToCart(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome.jsp");
        dispatcher.forward(request, response);
//        extracted(request,response);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.selectProduct(id);
        request.setAttribute("product", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/delete.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.selectProduct(id);
        request.setAttribute("product", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productDAO.selectAllProduct();
        request.setAttribute("products", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/list.jsp");
        requestDispatcher.forward(request, response);

    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        List<Product> products = productDAO.searchByName(search);
        request.setAttribute("products", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/welcome.jsp");
        requestDispatcher.forward(request, response);
    }
    private void searchProductAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        List<Product> products = productDAO.searchByName(search);
        request.setAttribute("products", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/list.jsp");
        requestDispatcher.forward(request, response);
    }

    private void newProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("create.jsp");
        rd.forward(request, response);

    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    private void backHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        extracted(request, response);
        RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
        rd.forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "add":
                    insertProductStore(request, response);
                    break;
                case "edit":
                    editProduct(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                case "cart":
             addCart(request, response);
                break;

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDAO.deleteProduct(id);

        List<Product> products = productDAO.selectAllProduct();
        request.setAttribute("products", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/list.jsp");
        requestDispatcher.forward(request, response);
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("desc");
        double price = Double.parseDouble(request.getParameter("price"));
        String image = request.getParameter("image");

        Product product = new Product(id, name, description, price, image);
        productDAO.updateProduct(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void insertProductStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        String name = request.getParameter("name");
        String description = request.getParameter("desc");
        double price = Double.parseDouble(request.getParameter("price"));
        String image = request.getParameter("image");
        Product newProduct = new Product(name, description, price, image);
        productDAO.insertProduct(newProduct);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/list.jsp");
//        dispatcher.forward(request, response);
        listProduct(request, response);
    }

    private void extracted(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productDAO.selectAllProduct();
        request.setAttribute("products", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/welcome.jsp");
        requestDispatcher.forward(request, response);
    }
}
