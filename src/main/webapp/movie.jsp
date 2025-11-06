<%@ page import="org.daw.firstweb.model.Movie" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: riku
  Date: 11/6/25
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Movie Jsp</title>
</head>
<body>
<h1>Movie JSP</h1>

    <p><c:out value="${movie.id}" /></p>
    <p><c:out value="${movie.title}" /></p>
    <p><c:out value="${movie.description}" /></p>

</body>
</html>
