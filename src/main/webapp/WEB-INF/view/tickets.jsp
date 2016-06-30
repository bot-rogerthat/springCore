<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<c:choose>
    <c:when test="${fn:length(tickets) > 0}">
        <h2>Tickets:</h2>
        <c:set var="count" value="0"/>
        <c:forEach var="ticket" items="${tickets}">
            <c:set var="count" value="${count + 1}"/>
            <h3>#${count}</h3>
            <ul>
                <li>Event id: ${ticket.eventId}</li>
                <li>Seat: ${ticket.seat}</li>
            </ul>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <h2>Tickets not found</h2>
    </c:otherwise>
</c:choose>
</body>
</html>