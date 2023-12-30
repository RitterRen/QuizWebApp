<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Login</title>
    <style>
        .centered {
            text-align: center;
        }
        .login-form {
            margin: 0 auto;
            width: 300px;
            text-align: left; /* Left-aligns content within the form */
        }
        .form-field {
            margin-bottom: 10px;
        }
        label, input {
            display: block; /* Makes label and input stack vertically */
        }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="centered">
    <h2>Login Page</h2>
    <form action="/login-check" method="post" class="login-form">
        <div class="form-field">
            <label for="email">Email:</label>
            <input type="text" id="email" name="email">
        </div>
        <div class="form-field">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password">
        </div>
        <c:if test="${loginError != null}">
            <div style="color: red; font-size: small;">${loginError}</div>
        </c:if>
        <div class="form-field">
            <input type="submit" value="Login">
        </div>
    </form>
</div>

</body>
</html>