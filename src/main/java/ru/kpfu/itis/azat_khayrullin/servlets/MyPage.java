package ru.kpfu.itis.azat_khayrullin.servlets;

import ru.kpfu.itis.azat_khayrullin.database.SQLHelper;
import ru.kpfu.itis.azat_khayrullin.exception.DBException;
import ru.kpfu.itis.azat_khayrullin.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/mypage")
public class MyPage extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            resp.sendRedirect("/main");
        }else {
            fillUserInformation(req, resp, user);
            req.getRequestDispatcher("/jsp/myPage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String btnValue = req.getParameter("button");
        clickonButton(btnValue, req, resp);
    }

    private void clickonButton(String btnValue, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        switch (btnValue){
            case "logout":
                req.getSession().setAttribute("user", null);
                resp.sendRedirect("/");
                break;
            case "delete me":
                HttpSession session = req.getSession();
                User user = (User)session.getAttribute("user");
                SQLHelper repository = new SQLHelper();
                try {
                    repository.delUser(user.getEmail(), user.getPassword());
                    req.getSession().setAttribute("user", null);
                    resp.sendRedirect("/");
                } catch (DBException e) {
                    e.printStackTrace();
                }
        }
    }

    private void fillUserInformation(HttpServletRequest req, HttpServletResponse resp, User user){
        req.setAttribute("name", user.getName());
        req.setAttribute("email", user.getEmail());
        req.setAttribute("phoneNumber", user.getPhoneNumber());
        req.setAttribute("addres", user.getAddres());
    }
}
