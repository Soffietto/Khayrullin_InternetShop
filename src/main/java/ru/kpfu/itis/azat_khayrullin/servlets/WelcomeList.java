package ru.kpfu.itis.azat_khayrullin.servlets;

import ru.kpfu.itis.azat_khayrullin.database.ProductDAO;
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

import static ru.kpfu.itis.azat_khayrullin.servlets.Bucket.bucket;
import static ru.kpfu.itis.azat_khayrullin.servlets.Bucket.cost;

public class WelcomeList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ProductDAO productDAO = new ProductDAO();
        try {
            List products = productDAO.getProductList();
            req.setAttribute("products", products);
        } catch (DBException e) {
            e.printStackTrace();
        }
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if(user.getEmail().equals("admin")){
                resp.sendRedirect("/admin");
            }else {
                req.getRequestDispatcher("/jsp/userjsp.jsp").forward(req, resp);
            }
        }else {
            req.getRequestDispatcher("/jsp/welcomeList.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session= req.getSession();
        session.invalidate();
        bucket.clear();
        cost=0;
        resp.sendRedirect("/main");
    }
}
