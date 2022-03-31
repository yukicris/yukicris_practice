package com.yukicris.servlet1;

import com.yukicris.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionDemo01 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");


        // 得到session
        HttpSession session = req.getSession();


        //给session中存东西
        session.setAttribute("name","yukicris");
        session.setAttribute("person",new Person("yukicris",26));

        //获取session的id
        String id = session.getId();

        //判断是不是新的session
        if (session.isNew()) {
            resp.getWriter().write("session创建成功,id为"+id);
        } else {
            resp.getWriter().write("session已存在,id为"+id);
        }

        // session创建的时候做了什么
        /*
        * {
        * 1 用cookie存了个id
        * Cookie cookie = new Cookie("JSESSION",session);
        * resp.addCookie(cookie)
        *
        * 2
        * }
        * */
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
