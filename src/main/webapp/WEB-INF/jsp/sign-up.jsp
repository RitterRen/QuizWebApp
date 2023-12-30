<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Sign Up</title>
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
    <h2>Sign Up</h2>
    <form action="/createUser" method="post" class="login-form">
        <div class="form-field">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName">
        </div>
        <div class="form-field">
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName">
        </div>
        <div class="form-field">
            <label for="email">Email:</label>
            <input type="text" id="email" name="email">
        </div>
        <div class="form-field">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password">
        </div>
        <div class="form-field">
            <input type="submit" value="Sign Up">
        </div>
    </form>
</div>

</body>
</html>
