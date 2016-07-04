<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<h2>Employee:</h2>
<table>
    <ul>
        <li>Name: ${employee.name}</li>
        <li>Jobs: <a href="${employee.id}/jobs">${fn:length(employee.jobs)}</a></li>
    </ul>
</table>
</body>
</html>