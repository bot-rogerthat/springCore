<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<form:form method="post" action="${id}/assign">
    <table>
        <tr>
            <td>Date (yyyy-mm-dd):</td>
            <td><input type="text" name="date" required/></td>
        </tr>
        <tr>
            <td>Time (hh:mm):</td>
            <td><input type="text" name="time" required/></td>
        </tr>
        <tr>
            <td>auditorium:</td>
            <td>
                <select name="auditorium">
                    <c:forEach items="${auditoriums}" var="auditorium">
                        <option value="${auditorium}">${auditorium.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>