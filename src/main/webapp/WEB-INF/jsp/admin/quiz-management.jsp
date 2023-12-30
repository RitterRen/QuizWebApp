<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz Result Management Page</title>
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
        .filter-form {
            margin: 20px auto;
            width: 50%;
            text-align: center;
        }
    </style>
</head>
<body>

<div class="centered-content">
    <h1>Quiz Result Management Page</h1>

    <!-- Filter Form -->
    <div class="filter-form">
        <form action="/filterQuizResult" method="get">
            <label for="category">Category:</label>
            <select name="categoryId" id="category">
                <option value="">All Categories</option>
                <c:forEach items="${categoryList}" var="category">
                    <option value="${category.id}">${category.name}</option>
                </c:forEach>
            </select>

            <label for="user">User:</label>
            <select name="userId" id="user">
                <option value="">All Users</option>
                <c:forEach items="${userList}" var="user">
                    <option value="${user.id}">${user.name}</option>
                </c:forEach>
            </select>

            <input type="submit" value="Filter">
        </form>
    </div>


    <!-- Quiz Results Table -->
    <table border="1">
        <tr>
            <th>Quiz Id</th>
            <th>Taken Time</th>
            <th>Category</th>
            <th>User Full Name</th>
            <th>No. of Question</th>
            <th>Score</th>
        </tr>
        <c:forEach items="${quizResultList}" var="quizResult">
            <tr>
                <td>
                    <a href="/quizResult/${quizResult.quizId}" style="color: blue; text-decoration: none;">
                        ${quizResult.quizId}
                    </a>
                </td>
                <td>${quizResult.takenTime}</td>
                <td>${quizResult.category}</td>
                <td>${quizResult.userName}</td>
                <td>${quizResult.questionListStr}</td>
                <td>${quizResult.score}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>