<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
   <h2>Users:</h2>
   <input type="submit" value="Add" onclick="location.href='users/addUser'"/>
   <c:forEach var="user" items="${users}">
       <spring:url value="users/deleteUser/${user.id}" var="urlDeleteUser"/>
       <h3><a href="users/${user.id}">${user.name}</a></h3>
       <ul>
           <li>${user.email}</li>
           <li><fmt:formatDate value="${user.dayOfBirth}" pattern="yyyy-MM-dd"/></li>
           <li>
               <input type="submit" value="Delete" onclick="location.href='${urlDeleteUser}'"/>
           </li>
       </ul>
   </c:forEach>
</body>
</html>