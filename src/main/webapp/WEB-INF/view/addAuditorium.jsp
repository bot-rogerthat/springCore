<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<form:form method="POST" action="addAuditorium" modelAttribute="auditorium">
    <table>
        <tr>
            <td>Auditorium name:</td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td>Number of seats:</td>
            <td><form:input path="numberOfSeats"/></td>
        </tr>
        <tr>
            <td>Vips (#,#,#):</td>
            <td><form:input path="vips"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>