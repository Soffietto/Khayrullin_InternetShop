package ru.kpfu.itis.azat_khayrullin.database;

import ru.kpfu.itis.azat_khayrullin.exception.AlreadyExistException;
import ru.kpfu.itis.azat_khayrullin.exception.DBException;
import ru.kpfu.itis.azat_khayrullin.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductDAO {

    public boolean addProduct(Product product) throws DBException, AlreadyExistException {
        Connection conn = SQLDatabase.getConnection();
        if (!containsProduct(product)) {
            try {
                PreparedStatement st = conn.prepareStatement("INSERT INTO products(name, cost, description) VALUES (?,?,?)");
                int i = 1;
                st.setString(i++, product.getName());
                st.setInt(i++, product.getCost());
                st.setString(i++, product.getDescription());
                st.execute();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DBException();
            }
        }
        return false;
    }

    public Product findByName(String name) throws DBException {
        Connection conn = SQLDatabase.getConnection();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM products WHERE name=?");
            int i = 1;
            st.setString(i++, name);
            ResultSet resultSet = st.executeQuery();
            if (resultSet != null) {
                if (resultSet.next()) {
                    Product product = new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("cost"),
                            resultSet.getString("description")
                    );
                    return product;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
        return null;
    }

    public boolean containsProduct(Product product) throws DBException {
        Connection conn = SQLDatabase.getConnection();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM products WHERE name=?");
            int i = 1;
            st.setString(i++, product.getName());
            ResultSet resultSet = st.executeQuery();
            if (resultSet == null)
                return false;
            else return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
    }

    public List<Product> getProductList() throws DBException {
        Connection conn = SQLDatabase.getConnection();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM products");
            ResultSet resultSet = st.executeQuery();
            List<Product> productList = new LinkedList<Product>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    Product product = new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("cost"),
                            resultSet.getString("description")
                    );
                    productList.add(product);
                }
                return productList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
        return null;
    }

    public boolean delProduct(String name) throws DBException {
        Connection conn = SQLDatabase.getConnection();
        try {
            PreparedStatement st = conn.prepareStatement("DELETE FROM products WHERE name=?");
            int i = 1;
            st.setString(i++, name);
            st.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
    }
}
