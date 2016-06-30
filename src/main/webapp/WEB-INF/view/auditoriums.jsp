<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<c:choose>
    <c:when test="${fn:length(auditoriums) > 0}">
        <h2>Auditoriums:</h2>
        <input type="submit" value="Add" onclick="location.href='auditoriums/addAuditorium'"/>
        <c:forEach var="auditorium" items="${auditoriums}">
            <spring:url value="auditoriums/deleteAuditorium/${auditorium.id}" var="urlDeleteAuditorium"/>
            <h3>${auditorium.name}</h3>
            <ul>
                <li>Seats: ${auditorium.numberOfSeats}</li>
                <li>Vips: ${auditorium.vips}</li>
                <li>
                    <input type="submit" value="Delete" onclick="location.href='${urlDeleteAuditorium}'"/>
                </li>
            </ul>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <h2>Users not found</h2>
    </c:otherwise>
</c:choose>
</body>
</html>