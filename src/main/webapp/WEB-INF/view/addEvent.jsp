<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<form:form method="POST" action="addEvent" modelAttribute="event">
    <table>
        <tr>
            <td>Event name:</td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td>Date:</td>
            <td><form:input path="date"/></td>
        </tr>
        <tr>
            <td>Time:</td>
            <td><form:input path="time"/></td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><form:input path="price"/></td>
        </tr>
        <tr>
            <td>Rating:</td>
            <td>
                <select name="rating">
                    <c:forEach items="${ratings}" var="elem">
                        <option value="${elem}">${elem}</option>
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