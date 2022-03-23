package com.yukicris.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProperitiesServlet extends HttpServlet {


   /* public test() {
        Properties properties = new Properties();
        properties.load("");
    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //super.doGet(req, resp);
        InputStream resourceAsStream = this.getServletContext().getResourceAsStream("/WEB-INF/classes/db.properities");

        Properties prop = new Properties();
        prop.load(resourceAsStream);
        String user = prop.getProperty("username");
        String password = prop.getProperty("password");

        resp.getWriter().print(user+"---"+password);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
    }
}
