package com.yukicris.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//              1 获取下载文件的路径
        String realPath = "D:\\workList\\yukicris_practice\\practice_3\\src\\main\\resources\\Test.jpg";
        //String realPath = this.getServletContext().getRealPath(path);
        System.out.println("下载路径为"+realPath);

//              2 下载的文件名是什么
        String fileName = realPath.substring(realPath.lastIndexOf("\\")+1);//转义,所以用2个杠
//              3 设置想办法让浏览器能够支持下载我们需要的东西,URLEncoder.encode这部分是为了让它对中文解码
        resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"utf-8"));
//              4 获取下载文件的输入流
        FileInputStream in = new FileInputStream(realPath);

//              5 创建缓冲区
        int len = 0;
        byte[] buffer = new byte[1024];

//              6 获取Outputstream对象
        ServletOutputStream out = resp.getOutputStream();
//              7 将FileOutputStream流写入buffer缓冲区
        while((len=in.read(buffer))>0){
            out.write(buffer,0,len);
        }
        in.close();
        out.close();
//              8 使用OutputStream将缓冲区中的数据输出到客户端


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
