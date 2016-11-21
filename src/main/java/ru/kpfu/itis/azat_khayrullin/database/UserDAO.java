package ru.kpfu.itis.azat_khayrullin.database;

import ru.kpfu.itis.azat_khayrullin.exception.AlreadyExistException;
import ru.kpfu.itis.azat_khayrullin.exception.DBException;
import ru.kpfu.itis.azat_khayrullin.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public boolean addUser(User user) throws DBException, AlreadyExistException {
        Connection conn = SQLDatabase.getConnection();
        if(!containsUser(user)){
            try {
                PreparedStatement st = conn.prepareStatement("INSERT INTO users(name, email, password, phonenumber, addres) VALUES (?,?,?,?,?)");
                int i =1;
                st.setString(i++, user.getName());
                st.setString(i++, user.getEmail());
                st.setString(i++, user.getPassword());
                st.setString(i++, user.getPhoneNumber());
                st.setString(i++, user.getAddres());
                st.execute();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DBException();
            }
        }else {
            throw new AlreadyExistException();
        }
        return false;
    }

    public User findByEmail(String email, String password) throws DBException{
        Connection conn = SQLDatabase.getConnection();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");
            int i =1;
            st.setString(i++, email);
            st.setString(i++, password);
            ResultSet resultSet = st.executeQuery();
            if(resultSet != null){
                if(resultSet.next()){
                    if(resultSet.getString("password").equals(/*Encryptor.getHash(password,email)*/password)){
                        User user = new User(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("phonenumber"),
                                resultSet.getString("addres")
                        );
                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
        return null;
    }

    public boolean containsUser(User user) throws DBException {
        Connection conn = SQLDatabase.getConnection();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM users WHERE email=?");
            int i =1;
            st.setString(i++, user.getEmail());
            ResultSet resultSet = st.executeQuery();
            if(resultSet == null)
                return false;
            else return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
    }

    public boolean delUser(String email, String password) throws DBException {
        Connection conn = SQLDatabase.getConnection();
        try {
            PreparedStatement st = conn.prepareStatement("DELETE FROM users WHERE email=? AND password=?");
            int i = 1;
            st.setString(i++, email);
            st.setString(i++, password);
            st.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
    }
}
