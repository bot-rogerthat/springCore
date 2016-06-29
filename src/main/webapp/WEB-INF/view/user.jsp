<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<h2>User:</h2>
<table>
    <ul>
        <li>Name: ${user.name}</li>
        <li>Email: ${user.email}</li>
        <li>Birth: <fmt:formatDate value="${user.dayOfBirth}" pattern="yyyy-MM-dd"/></li>
        <li>Tickets: <a href="${user.id}/tickets">${fn:length(user.tickets)}</a></li>
    </ul>
</table>
</body>
</html>