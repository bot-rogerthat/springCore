<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
   <h2>Events:</h2>
   <input type="submit" value="Add" onclick="location.href='events/addEvent'"/>
   <c:set var="count" value="0"/>
   <c:forEach var="event" items="${events}">
       <spring:url value="events/deleteEvent/${event.id}" var="urlDeleteEvent"/>
       <c:set var="count" value="${count + 1}"/>
       <h3>#${count}</h3>
       <ul>
           <li>Event name: ${event.name}</li>
           <li>Date: <fmt:formatDate value="${event.date}" pattern="yyyy-MM-dd"/></li>
           <li>Time: ${event.time}</li>
           <li>Price: ${event.price}</li>
           <li>Rating: ${event.rating}</li>
           <li>Auditorium: ${event.auditoriumId}</li>
           <li><a href="events/${event.id}/bookedTickets">tickets</a></li>
           <li><a href="events/${event.id}">assignAuditorium</a></li>
           <li>
               <input type="submit" value="Delete" onclick="location.href='${urlDeleteEvent}'"/>
           </li>
       </ul>
   </c:forEach>
</body>
</html>