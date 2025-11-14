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
    <title>Movies</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<h1>Movies</h1>

<table class="movie-table">
    <tr>
        <th>ID</th>
        <th>Título</th>
        <th>Descripción</th>
        <th>Año</th>
        <th>Acciones</th>
    </tr>

    <c:forEach var="movie" items="${movies}">
        <tr>
            <td>${movie.id}</td>
            <td>${movie.title}</td>
            <td>${movie.description}</td>
            <td>${movie.year}</td>
            <td>
                <form action="${pageContext.request.contextPath}/movies" method="get" style="display:inline;">
                    <input type="hidden" name="id" value="${movie.id}">
                    <button type="submit" class="btn btn-view">Ver</button>
                </form>
                <form action="movies" method="post" class="delete-form">
                    <input type="hidden" name="_method" value="DELETE"/>
                    <input type="hidden" name="id" value="${movie.id}"/>
                    <button type="submit" class="delete-btn">Eliminar</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Añadir nueva película</h2>

<form action="movies" method="post" class="add-form">

    <label>Título:</label>
    <input type="text" name="title" required />

    <label>Descripción:</label>
    <input type="text" name="description" required />

    <label>Año:</label>
    <input type="text" name="year" required />

    <input type="submit" value="Añadir película" class="submit-btn"/>
</form>

</body>
</html>
