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
import java.util.List;

import static ru.kpfu.itis.azat_khayrullin.servlets.Bucket.bucket;
import static ru.kpfu.itis.azat_khayrullin.servlets.Bucket.cost;
import static ru.kpfu.itis.azat_khayrullin.servlets.Bucket.productDAO;

@WebServlet("/removebucket")
public class BucketRemove extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/bucket");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String btnValue = req.getParameter("button");
        try {
            Product product = productDAO.findByName(btnValue);
            int index=-1;
            for (Product a: bucket) {
                if(a.getName().equals(product.getName())){
                    index = bucket.indexOf(a);
                }
            }
            bucket.remove(index);
            req.setAttribute("bucket",bucket);
            cost -= product.getCost();
            req.setAttribute("cost",cost);
            resp.sendRedirect("/bucket");
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
