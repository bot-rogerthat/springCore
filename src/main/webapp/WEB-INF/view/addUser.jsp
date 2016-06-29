<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<form:form method="POST" action="addUser" modelAttribute="user">
    <table>
        <tr>
            <td>User name:</td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td>E-mail:</td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td>Birthday (yyyy-mm-dd):</td>
            <td><form:input path="dayOfBirth"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>