<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <title>Navbar</title>
  <style>
    .navbar {
      display: flex;
      justify-content: space-between;
      align-items: center;
      background-color: #333;
      overflow: hidden;
    }
    .nav-list {
      list-style-type: none;
      margin: 0;
      padding: 0;
    }
    .nav-list li {
      float: left;
    }
    .nav-list li a {
      display: block;
      color: white;
      text-align: center;
      padding: 14px 16px;
      text-decoration: none;
    }
    .nav-list li a:hover {
      background-color: #ddd;
      color: black;
    }
    .navbar-right {
      color: white;
      padding-right: 20px;
    }
  </style>
</head>
<body>
<div class="navbar">
  <ul class="nav-list">
    <c:if test="${sessionScope.user != null}">
      <li><a href="/home">Home</a></li>
    </c:if>
    <c:if test="${sessionScope.quiz != null}">
      <li><a href="/quiz">Taking Quiz</a></li>
    </c:if>
    <c:choose>
      <c:when test="${sessionScope.user == null}">
        <li><a href="/login">Login</a></li>
      </c:when>
      <c:otherwise>
        <li><a href="/logout">Logout</a></li>
      </c:otherwise>
    </c:choose>
    <c:if test="${empty sessionScope.user}">
      <li><a href="/signup">Register</a></li>
    </c:if>
    <li><a href="/contact">Contact Us</a></li>
  </ul>

  <div class="navbar-right">
    <c:if test="${not empty sessionScope.user}">
      Current User: ${sessionScope.user.name}
    </c:if>
  </div>
</div>
</body>
</html>
