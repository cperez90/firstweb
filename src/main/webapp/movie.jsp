<%@ page import="org.daw.firstweb.model.Movie" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: riku
  Date: 11/6/25
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie Jsp</title>
</head>
<body>
<h1>Movie JSP</h1>
<% List<Movie> movies = (List<Movie>) request.getAttribute("movies"); %>
<% for(Movie movie: movies) { %>
    <%= movie.getTitle() + "<br>"%>
<% } %>
</body>
</html>
