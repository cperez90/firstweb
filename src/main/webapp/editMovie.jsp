<%--
  Created by IntelliJ IDEA.
  User: riku
  Date: 11/15/25
  Time: 10:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Editar Película</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>

<h1>Editar Película</h1>

<form action="movies" method="post">
    <input type="hidden" name="_method" value="PUT">
    <input type="hidden" name="id" value="${movie.id}">

    <label>Título:</label>
    <input type="text" name="title" value="${movie.title}" required>

    <label>Descripción:</label>
    <textarea name="description" required>${movie.description}</textarea>

    <label>Año:</label>
    <input type="number" name="year" value="${movie.year}" required>

    <button type="submit" class="btn" style="margin-top: 20px;">Guardar cambios</button>
</form>

<div class="back-wrapper">
    <a href="movies?id=${movie.id}" class="btn-back">Volver</a>
</div>

</body>
</html>
