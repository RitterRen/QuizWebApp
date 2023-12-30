<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contact Management Page</title>
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
    </style>
</head>
<body>

<div class="centered-content">
    <h1>Contact Management Page</h1>
    <table border="1">
        <tr>
            <th>Subject</th>
            <th>Email Address</th>
            <th>Time</th>
            <th>Message Content</th>
        </tr>
        <c:forEach items="${contactList}" var="contact">
            <tr>
                <td>${contact.subject}</td>
                <td>${contact.email}</td>
                <td>${contact.contactTime}</td>
                <td>${contact.message}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>

