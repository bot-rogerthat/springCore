<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<ul>
    <li><a href="auditoriums">Auditoriums</a></li>
    <li><a href="events">Events</a></li>
    <li><a href="users">Users</a></li>
</ul>
<h3>Upload auditoriums:</h3>
<form enctype="multipart/form-data" action="upload/auditoriums" method="POST">
    <input type="file" name="file"/>
    <input type="submit" value="Submit"/>
</form>
<h3>Upload events:</h3>
<form enctype="multipart/form-data" action="upload/events" method="POST">
    <input type="file" name="file"/>
    <input type="submit" value="Submit"/>
</form>
<h3>Upload users:</h3>
<form enctype="multipart/form-data" action="upload/users" method="POST">
    <input type="file" name="file"/>
    <input type="submit" value="Submit"/>
</form>
<h3>Auditoriums pdf:</h3>
<form action="auditoriums/byAuditorium.pdf" method="GET">
    <input type="submit" value="submit"/>
</form>
<h3>Events pdf:</h3>
<form action="events/byEvent.pdf" method="GET">
    <input type="submit" value="submit"/>
</form>
<h3>Users pdf:</h3>
<form action="users/byUser.pdf" method="GET">
    <input type="submit" value="submit"/>
</form>
</body>
</html>