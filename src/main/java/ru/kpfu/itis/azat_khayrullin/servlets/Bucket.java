package ru.kpfu.itis.azat_khayrullin.servlets;

import ru.kpfu.itis.azat_khayrullin.database.ProductDAO;
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
import java.util.LinkedList;
import java.util.List;

@WebServlet("/bucket")
public class Bucket extends HttpServlet {
    static List<Product> bucket= new LinkedList<Product>();
    static ProductDAO productDAO = new ProductDAO();
    static int cost;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/main");
        } else {
            req.setAttribute("bucket",bucket);
            req.setAttribute("cost", cost);
            req.getRequestDispatcher("/jsp/bucket.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String buttonValue = req.getParameter("button");
        try {
            bucket.add(productDAO.findByName(buttonValue));
            req.setAttribute("bucket",bucket);
            cost += productDAO.findByName(buttonValue).getCost();
            req.setAttribute("cost", cost);
            resp.sendRedirect("/main");
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
