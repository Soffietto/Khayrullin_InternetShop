package ru.kpfu.itis.azat_khayrullin.servlets;

import ru.kpfu.itis.azat_khayrullin.Service.RegularTester;
import ru.kpfu.itis.azat_khayrullin.database.ProductDAO;
import ru.kpfu.itis.azat_khayrullin.database.UserDAO;
import ru.kpfu.itis.azat_khayrullin.exception.AlreadyExistException;
import ru.kpfu.itis.azat_khayrullin.exception.DBException;
import ru.kpfu.itis.azat_khayrullin.models.Product;
import ru.kpfu.itis.azat_khayrullin.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addproduct")
public class AddProduct extends HttpServlet {
    private String name;
    private String cost;
    private String description;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/main");
        } else {
            req.getRequestDispatcher("/jsp/addproduct.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        name = req.getParameter("productName");
        cost = req.getParameter("productCost");
        description = req.getParameter("productDescription");

        try {
            addProduct(req);
            resp.sendRedirect("/admin");
        } catch (DBException e) {
            e.printStackTrace();
            doGet(req, resp);
        } catch (AlreadyExistException e) {
            e.printStackTrace();
            doGet(req, resp);
        }
    }

    private void addProduct(HttpServletRequest req) throws DBException, AlreadyExistException {
        ProductDAO productDAO = new ProductDAO();
        Product product = new Product(name, Integer.parseInt(cost), description);
        productDAO.addProduct(product);
    }
}
