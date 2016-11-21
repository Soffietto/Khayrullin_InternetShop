package ru.kpfu.itis.azat_khayrullin.servlets;

import ru.kpfu.itis.azat_khayrullin.Service.RegularTester;
import ru.kpfu.itis.azat_khayrullin.database.UserDAO;
import ru.kpfu.itis.azat_khayrullin.exception.AlreadyExistException;
import ru.kpfu.itis.azat_khayrullin.exception.DBException;
import ru.kpfu.itis.azat_khayrullin.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registration")
public class Registration extends HttpServlet {
    private String email;
    private String firstName;
    private String secondName;
    private String password;
    private String repassword;
    private String phoneNumber;
    private String addres;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null){
            resp.sendRedirect("/main");
        }else {
            fillEnteredInformation(req);
            req.getRequestDispatcher("/jsp/register.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        email = req.getParameter("email");
        firstName = req.getParameter("firstName");
        secondName = req.getParameter("secondName");
        password = req.getParameter("password");
        repassword = req.getParameter("repassword");
        phoneNumber = req.getParameter("phoneNumber");
        addres = req.getParameter("addres");

        RegularTester ret = new RegularTester();
        boolean checker = true;

        if(!ret.isCorrectEmail(email)){
            checker = false;
            email = null;
            showError(req, "email", "Invalid Email!");
        }

        if(!ret.isCorrectName(firstName)){
            checker = false;
            firstName = null;
            showError(req, firstName, "Invalid first name!");
        }

        if(!ret.isCorrectName(secondName)){
            checker = false;
            secondName = null;
            showError(req, secondName, "Invalid second name!");
        }

        if (!ret.isCorrectPassword(password)) {
            checker = false;
            showError(req, "password", "Invalid password");
        } else {
            if (!password.equals(repassword)) {
                checker = false;
                showError(req, "repassword", "Passwords don't equal");
            }
        }

        if(checker){
            try {
                addUser(req);
                resp.sendRedirect("/login");
            } catch (DBException e) {
                e.printStackTrace();
                doGet(req, resp);
            } catch (AlreadyExistException e) {
                showError(req, "email", "This email already used");
                doGet(req, resp);
            }
        }else {
            doGet(req, resp);
        }
    }

    private void fillEnteredInformation(HttpServletRequest req) {
        req.setAttribute("email", email);
        req.setAttribute("firstName", firstName);
        req.setAttribute("secondName", secondName);
        req.setAttribute("phoneNumber", phoneNumber);
        req.setAttribute("addres", addres);
    }

    private void showError(HttpServletRequest req, String name, String errMessage) {
        req.setAttribute("err" + name, errMessage);
    }

    private void addUser(HttpServletRequest req) throws DBException, AlreadyExistException {
        UserDAO base = new UserDAO();
        User user = new User(secondName + " " + firstName, email, password, phoneNumber, addres);
        base.addUser(user);
        req.getSession().setAttribute("user",user);
    }

}
