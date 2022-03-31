package com.yukicris.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//统计网站在线人数: 统计session
public class OnlineCountListener implements HttpSessionListener {

    //创建session监听,一旦session被创建,就会触发这个事件
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext servletContext = se.getSession().getServletContext();
        Integer onlineCount = (Integer)servletContext.getAttribute("onlineCount");

        if (onlineCount==null) {
            onlineCount = new Integer(1);
        } else {
            int count = onlineCount.intValue();
            count = new Integer(count+1);
        }

        servletContext.setAttribute("onLineCount",onlineCount);
    }

    //销毁session监听
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
