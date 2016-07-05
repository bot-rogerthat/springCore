<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="<c:url value='/resources/css/table.css'/>">
</head>
<body>
<h2>Jobs:</h2>
<c:choose>
    <c:when test="${aboutEmployee}">
        <h2>Employee:</h2>
    </c:when>
    <c:otherwise>
        <input type="submit" value="Add" onclick="location.href='jobs/addJob'"/>
    </c:otherwise>
</c:choose>

<table>
    <tr>
        <th>Description</th>
        <th>Date</th>
        <th>Employee</th>
        <c:choose>
            <c:when test="${aboutEmployee}">
            </c:when>
            <c:otherwise>
                <th>Actions</th>
            </c:otherwise>
        </c:choose>
    </tr>
    <c:forEach var="job" items="${jobs}">
        <spring:url value="jobs/assignJob/${job.id}" var="urlAssignJob"/>
        <spring:url value="jobs/deleteJob/${job.id}" var="urlDeleteJob"/>
        <tr>
            <td>${job.description}</td>
            <td>${job.date}</td>
            <td>${job.employee.name}</td>
            <c:choose>
                <c:when test="${aboutEmployee}">
                </c:when>
                <c:otherwise>
                    <td>
                        <div class="buttons">
                            <input class="assign" type="submit" value="Assign"
                                   onclick="location.href='${urlAssignJob}'"/>
                            <input class="delete" type="submit" value="Delete"
                                   onclick="location.href='${urlDeleteJob}'"/>
                        </div>
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
</body>
</html>