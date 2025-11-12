<%--
  Created by IntelliJ IDEA.
  User: riku
  Date: 11/6/25
  Time: 7:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Movies JSP</h1>
<%--<% List<Movie> movies = (List<Movie>) request.getAttribute("movies"); %>
<% for(Movie movie: movies) { %>
    <%= movie.getTitle() + "<br>"%>
<% } %>--%>
<p>
    <c:forEach var="movie" items="${movies}">
        ${movie.id}<br>
        ${movie.title}<br>
        ${movie.description}<br>
    </c:forEach>
</p>
<form action="movie" method="post">
    <input type="text" name="title"/>
    <input type="text" name="description"/>
    <input type="text" name="year">
</form>
</body>
</html>
