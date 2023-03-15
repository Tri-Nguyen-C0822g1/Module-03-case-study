package controller;

import dao.ProductDAO;
import model.Product;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    final private String errorPage = "fail.jsp";
    final private String homePage = "index.jsp";
    final private String welcomePage = "welcome.jsp";
    final private String registerPage = "register.jsp";
    ProductDAO productDAO = new ProductDAO();
    User user;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String action = request.getParameter("btAction");
            if (action.equals("Login")) {
                String username = request.getParameter("txtUsername");
                String password = request.getParameter("txtPass");
                LoginBean login = new LoginBean();
                user = login.checkLogin(username, password);
                String url;
                if (user != null) {
                    if (!user.isAdmin()) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("USER", username);

                        extracted(request, response);
                        url = welcomePage;
                    } else {
                        url = "/list.jsp";
                    }
                } else {
                    url = errorPage;
                }
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);

            } else if (action.equals("tryAgain")) {
                RequestDispatcher rd = request.getRequestDispatcher(homePage);
                rd.forward(request, response);
            } else if (action.equals("register")) {
                RequestDispatcher rd = request.getRequestDispatcher(registerPage);
                rd.forward(request, response);
            } else if (action.equals("Register")) {
                String username = request.getParameter("txtUser");
                String password = request.getParameter("txtPass");
                String lastname = request.getParameter("txtLast");
                String admin = request.getParameter("chkAdmin");
                boolean roles = false;
                if (admin != null) {
                    roles = true;
                }
                LoginBean login = new LoginBean();
                boolean result = login.insert(username, password, lastname, roles);
                RequestDispatcher rd = request.getRequestDispatcher(homePage);
                rd.forward(request, response);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

    }

    private void extracted(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productDAO.selectAllProduct();
        request.setAttribute("products", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/welcome.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        try {
            String action = request.getParameter("btAction");

            if (action.equals("tryAgain")) {
                RequestDispatcher rd = request.getRequestDispatcher(homePage);
                rd.forward(request, response);
            } else if (action.equals("register")) {
                RequestDispatcher rd = request.getRequestDispatcher(registerPage);
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }


    }
}