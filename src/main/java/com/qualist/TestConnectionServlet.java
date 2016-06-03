package com.qualist;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by halil_000 on 5/30/2016.
 */
public class TestConnectionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long begin = System.currentTimeMillis();
        List<User> users = getUsers();
        System.out.println("duration:" + (System.currentTimeMillis() - begin));
        request.setAttribute("users", users);
        String testConnectionJSP = "/testConnection.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(testConnectionJSP);
        dispatcher.forward(request, response);
    }


    private List<User> getUsers() {
        List<User> userList = new ArrayList<User>();
        String sql = "Select * from users";
        Connection connection = null;
        try {
            connection = DbConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString(2);
                String surname = rs.getString(3);
                User user = new User();
                user.setName(name);
                user.setSurname(surname);
                userList.add(user);
            }
//            DbConnectionUtil.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                Thread.sleep(5000);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return userList;
    }


}
