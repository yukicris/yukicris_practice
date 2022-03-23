package com.yukicris.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //this.getInitParameter() 初始化参数

        //this.getServletConfig()  获取servlet参数

        //servletContext    servlet上下文,(它凌驾servlet上,类似中间商,可以做数据共享)
        String username = "yukicris";
        ServletContext context = this.getServletContext();

        context.setAttribute("username",username);  // 将一个数据保存在了servletContext中,名字为username,值username(对象)




        System.out.printf("hello");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
