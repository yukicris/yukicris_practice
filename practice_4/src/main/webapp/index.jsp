<%--
  Created by IntelliJ IDEA.
  User: Yukicris
  Date: 2022/3/23
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>

<div style="text-align: center">
    <%--以post方式提交表单,提交到login请求--%>
    <form action="${pageContext.request.contextPath}/login" method="post">
        用户名:<input type="text" name = "username"></input><br>
        密码:<input type="password" name = "password"></input><br>
        爱好:
        <input type="checkbox" name = "hobbies" value="打球">打球</input>
        <input type="checkbox" name = "hobbies" value="花钱">花钱</input>
        <input type="checkbox" name = "hobbies" value="唱歌">唱歌</input>
        <input type="checkbox" name = "hobbies" value="电影">电影</input>
        <br>
        <input type="submit">
    </form>
</div>
<body>

</body>
</html>
