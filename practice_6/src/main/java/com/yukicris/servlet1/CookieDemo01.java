package com.yukicris.servlet1;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/// 保存用户上一次访问所记录的时间
public class CookieDemo01 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 服务器告诉你,你来的时间,把这个时间封装成一个信件

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-16");//还乱码就设置gbk

        PrintWriter out = resp.getWriter();

        //Cookie,服务器端从客户端获取
        Cookie[] cookies = req.getCookies();  //这里返回数组,说明cookie可能存在多个

        //判断Cookie是否存在
        if (cookies!=null) {
            //如果存在怎么办
            out.write("上一次访问的时间是:");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                //获取cookie的名字
                if (cookie.getName().equals("lastLoginTime")) {
                    //获取cookie中的值
                    long aLong = Long.parseLong(cookie.getValue());
                    Date date = new Date(aLong);
                    out.write(date.toLocaleString());

                }
            }
        }else{
            out.write("这是第一次访问本站");
        }

        Cookie cookie = new Cookie("lastLoginTime", System.currentTimeMillis()+"");
        //服务器给客户端响应一个cookie
        cookie.setMaxAge(60*60*24);  //设置有效期,如果设置为0,表示跟随系统默认，其销毁与Session销毁时间相同，即都在浏览器关闭后的特定时间删除。如果我们写程序的时候不设置Cookie的有效时间，那么，Cookie的有效时间等效于会话时间。。
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
