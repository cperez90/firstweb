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
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Detalle de Película</h1>
<div class="movie-card">
    <p><strong>ID: </strong><c:out value="${movie.id}" /></p>
    <p><strong>Título: </strong><c:out value="${movie.title}" /></p>
    <p><strong>Descripción: </strong> ${movie.description}</p>
    <p><strong>Año: </strong><c:out value="${movie.year}" /></p>
</div>

<form action="movies" method="post" class="delete-form">
    <input type="hidden" name="_method" value="DELETE"/>
    <input type="hidden" name="id" value="${movie.id}"/>
    <button type="submit" class="delete-btn">Eliminar esta película</button>
</form>

<p><a href="movies" class="btn">Volver a la lista</a></p>

</body>
</html>