package com.yukicris.servlet2;

import com.sun.org.apache.regexp.internal.RE;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //这块登录拦截做的有点问题,只能参考一下
        //super.doGet(req, resp);
        //获取前端请求的参数
        String username = req.getParameter("username");
        if(username.equals("admin")) {
            //登录成功
            req.getSession().setAttribute("USER_SESSION",req.getSession().getId());
            resp.sendRedirect("/sys/success.jsp");
        } else {
            //登录失败
            resp.sendRedirect("/sys/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
