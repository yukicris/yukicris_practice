package com.yukicris.servlet1;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieDemo02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //删除cookie,用一个新的带有有效期的替换掉原来的cookie

        //1 创建一个cookie,名字必须要和删除的名字一致
        Cookie lastLoginTime = new Cookie("lastLoginTime", System.currentTimeMillis() + "");
        lastLoginTime.setMaxAge(0);

        resp.addCookie(lastLoginTime);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
