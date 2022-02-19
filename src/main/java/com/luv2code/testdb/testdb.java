package com.luv2code.testdb;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "TestDbServlet", value = "/TestDbServlet")
public class testdb extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // setup connection variables
        String user = "springstudent";
        String password = "springstudent";

        String jdbcURL = "jdbc:mysql://localhost:3306/web_customer_tracker?allowPublicKeyRetrieval=true&useSSL=false";
        String driver = "com.mysql.cj.jdbc.Driver";

        // get a connection to database
        try {
            PrintWriter out = response.getWriter();

            out.println("Connecting to database: " + jdbcURL);

            Class.forName(driver);

            Connection connection = DriverManager.getConnection(jdbcURL, user, password);

            out.println("SUCCESS!!!");

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }
}
