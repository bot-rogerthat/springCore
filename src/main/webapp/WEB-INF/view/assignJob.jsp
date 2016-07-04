<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<form:form method="post" action="${job.id}">
    <table>
        <tr>
            <td>Job:</td>
            <td>${job.description}</td>
        </tr>
        <tr>
            <td>Employee:</td>
            <td>
                <select name="employee">
                    <c:forEach items="${employees}" var="employee">
                        <option value="${employee.id}">${employee.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Date (yyyy-mm-dd hh:mm:ss):</td>
            <td><input type="text" name="date" required/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>