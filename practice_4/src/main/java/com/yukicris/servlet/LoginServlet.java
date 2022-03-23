package com.yukicris.servlet;

import org.apache.commons.lang.ArrayUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 后台接收中文乱码问题
        req.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String[] hobbies = req.getParameterValues("hobbies");

        System.out.print(username+"/n"+ password+ "/n"+ Arrays.toString(hobbies));


        //通过请求转发
        // 这里的"/" 代表当前的web应用,转发不用带项目名,重定向要带
        req.getRequestDispatcher("/success.jsp").forward(req,resp);
        //结束解决中文乱码问题
        resp.setCharacterEncoding("utf-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
