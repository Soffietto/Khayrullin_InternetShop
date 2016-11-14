package ru.kpfu.itis.azat_khayrullin.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class WelcomeList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("user") != null){
            req.getRequestDispatcher("/jsp/mainUser.jsp").forward(req,resp);
            return;
        }
        req.getRequestDispatcher("/jsp/welcomeList.jsp").forward(req,resp);
    }
}
