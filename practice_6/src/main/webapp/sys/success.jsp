<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    Object user_session = request.getSession().getAttribute("USER_SESSION");
    if (user_session == null) {
        response.sendRedirect("/Login.jsp");
    }
%>

<h1>主页</h1>
<p>
    <a href="/servlet/login"></a>
</p>
</body>

</html>
