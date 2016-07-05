<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="<c:url value='/resources/css/table.css'/>">
</head>
<body>
<table>
    <tr>
        <th>Entity</th>
        <th>Pdf view</th>
        <th>Upload</th>
    </tr>
    <tr>
        <td><a href="auditoriums">Auditoriums</a></td>
        <td><a href="auditoriums/byAuditorium.pdf">pdf</a></td>
        <td>
            <form enctype="multipart/form-data" action="upload/auditoriums" method="POST">
                <input type="file" name="file"/>
                <input type="submit" value="Submit"/>
            </form>
        </td>
    </tr>
    <tr>
        <td><a href="events">Events</a></td>
        <td><a href="events/byEvent.pdf">pdf</a></td>
        <td>
            <form enctype="multipart/form-data" action="upload/events" method="POST">
                <input type="file" name="file"/>
                <input type="submit" value="Submit"/>
            </form>
        </td>
    </tr>
    <tr>
        <td><a href="users">Users</a></td>
        <td><a href="users/byUser.pdf">pdf</a></td>
        <td>
            <form enctype="multipart/form-data" action="upload/users" method="POST">
                <input type="file" name="file"/>
                <input type="submit" value="Submit"/>
            </form>
        </td>
    </tr>
</table>
<ul>
    <li><a href="employees">Employees</a></li>
    <li><a href="jobs">Jobs</a></li>
</ul>
</body>
</html>