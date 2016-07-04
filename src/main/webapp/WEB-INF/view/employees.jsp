<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="<c:url value="/resources/css/table.css" />">
</head>
<body>
<h2>Employees:</h2>
<input type="submit" value="Add" onclick="location.href='employees/addEmployee'"/>
<table>
    <tr>
        <th>Name</th>
        <th>Jobs</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="employee" items="${employees}">
        <spring:url value="employees/deleteEmployee/${employee.id}" var="urlDeleteEmployee"/>
        <tr>
            <td><a href="employees/${employee.id}">${employee.name}</td>
            <td><a href="employees/${employee.id}/jobs">${fn:length(employee.jobs)}</a></td>
            <td><input type="submit" value="Delete" onclick="location.href='${urlDeleteEmployee}'"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>