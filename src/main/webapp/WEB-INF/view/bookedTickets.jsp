<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<h2>Booked tickets:</h2>
<c:choose>
    <c:when test="${fn:length(bookedTickets) > 0}">
        <c:set var="count" value="0"/>
        <c:forEach var="ticket" items="${bookedTickets}">
            <c:set var="count" value="${count + 1}"/>
            <h3>#${count}</h3>
            <ul>
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