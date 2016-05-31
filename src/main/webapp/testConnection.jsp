<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: halil_000
  Date: 5/25/2016
  Time: 10:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Connection Pooling</title>
</head>
<body>
<table>
    <tr>
    <td><b>Name</b></td>
    <td><b>Surname</b></td>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.surname}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
