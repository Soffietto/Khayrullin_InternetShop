package ru.kpfu.itis.azat_khayrullin.servlets;

import ru.kpfu.itis.azat_khayrullin.database.ProductDAO;
import ru.kpfu.itis.azat_khayrullin.database.UserDAO;
import ru.kpfu.itis.azat_khayrullin.exception.DBException;
import ru.kpfu.itis.azat_khayrullin.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminPage extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        ProductDAO productDAO = new ProductDAO();
        try {
            List products = productDAO.getProductList();
            req.setAttribute("products", products);
        } catch (DBException e) {
            e.printStackTrace();
        }
        if(!user.getName().equals("Azat Khayrullin")){
            resp.sendRedirect("/main");
        }else {
            req.getRequestDispatcher("/jsp/admin.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String buttonValue = req.getParameter("button");
        ProductDAO productDAO = new ProductDAO();
        try {
            productDAO.delProduct(buttonValue);
            doGet(req, resp);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
