<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>User Management Page</title>
  <style>
    .centered-content {
      text-align: center;
      margin: auto;
    }
    table {
      margin-left: auto;
      margin-right: auto;
      width: 80%;
    }
    th, td {
      padding: 15px;
      text-align: left;
    }
    th {
      background-color: #f0f0f0;
    }
    .button {
      padding: 5px 10px;
      margin: 5px;
      cursor: pointer;
    }
  </style>
</head>
<body>

<div class="centered-content">
  <h1>User Management Page</h1>
  <table border="1">
    <tr>
      <th>ID</th>
      <th>Full Name</th>
      <th>Email</th>
      <th>Status</th>
      <th>Admin</th>
      <th>Action</th>
    </tr>
    <c:forEach items="${userList}" var="user">
      <tr>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.email}</td>
        <td>${user.active ? "Activate" : "Suspend"}</td>
        <td>${user.admin ? 'Yes' : 'No'}</td>
        <td>
          <c:choose>
            <c:when test="${user.active}">
              <form action="/suspendUser/${user.id}" method="post">
                <input type="hidden" name="userId" value="${user.id}">
                <input type="submit" value="Suspend" class="button">
              </form>
            </c:when>
            <c:otherwise>
              <form action="/activateUser/${user.id}" method="post">
                <input type="hidden" name="userId" value="${user.id}">
                <input type="submit" value="Activate" class="button">
              </form>
            </c:otherwise>
          </c:choose>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>

</body>
</html>

