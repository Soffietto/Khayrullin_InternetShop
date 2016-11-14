package ru.kpfu.itis.azat_khayrullin.servlets;

import ru.kpfu.itis.azat_khayrullin.database.SQLHelper;
import ru.kpfu.itis.azat_khayrullin.exception.DBException;
import ru.kpfu.itis.azat_khayrullin.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if(user != null)
            resp.sendRedirect("/mypage");
        else
            req.getRequestDispatcher("/jsp/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email != null && password != null) {
            try {
                User user = getUser(email, password);
                if (user != null) {
                    req.getSession().setAttribute("user", user);
                    resp.sendRedirect("/mypage");
                } else {
                    req.setAttribute("wrong", "User not found");
                    doGet(req, resp);
                }
            } catch (DBException e) {
                e.printStackTrace();
                req.setAttribute("wrong", "Database Error");
                doGet(req, resp);
            }
        }
    }

    private User getUser(String email, String password) throws DBException {
        SQLHelper repository = new SQLHelper();
        return repository.findByEmail(email, password);
    }
}
