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
        ${movie.year}<br>
    </c:forEach>
</p>
<h2>Add Movie</h2>
<form action="movies" method="post">

    <p>Title: <label>
        <input type="text" name="title"/>
    </label></p>
    <p>Description:
    <label>
        <input type="text" name="description"/>
    </label>
    </p>
    <p>Year:
    <label>
        <input type="text" name="year" />
    </label>
    </p>
    <input type="submit" name="submit" />

</form>
<h2>Delete Movie</h2>
<form action="movies" method="post">
    <p>Id Movie:
        <label>
            <input type="text" name="id">
        </label>
    </p>
    <input type="hidden" name="_method" value="DELETE">
    <input type="submit" name="submit" />
</form>
</body>
</html>
