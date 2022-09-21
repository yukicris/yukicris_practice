package com.yukicris.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDemo04 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        System.out.printf("进入了demo4");
        RequestDispatcher requestDispatcher = context.getRequestDispatcher("/getservlet");  // 转发的请求路径
        requestDispatcher.forward(req,resp);  //调用forward实现请求转发,转发浏览器地址是不会变的,重定向会变
        //重定向指的是用resp.sendRedirect("out1?mess="+message); 这种方法
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
