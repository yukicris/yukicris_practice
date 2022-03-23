<html>
<body>
<h2>Hello World!</h2>

<%--这里提交的路径需要寻找到项目的路径--%>
<%--这个代表当前项目,最推荐的写法${pageContext.request.contextPath}+servlet地址--%>
<form action="${pageContext.request.contextPath}/login" method="get">
    用户名:<input type="text" name ="username">
    密码:<input type="password" name ="password">
    <input type="submit">
</form>
</body>
</html>
