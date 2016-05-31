package com.qualist;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
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
public class ConnectionServlet extends HttpServlet {

    DataSource dataSource;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("---------------------------------------------------");
        System.out.println("init method has been called and servlet is initialized");
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/DSTest");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------------------------------------");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = getUsers();
        request.setAttribute("users", users);

        String testConnectionJSP = "/testConnection.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(testConnectionJSP);
        dispatcher.forward(request, response);
    }


    private List<User> getUsers() {
        List<User> userList = new ArrayList<User>();
        String sql = "Select * from users";

        try {
            Connection connection = dataSource.getConnection();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

}
