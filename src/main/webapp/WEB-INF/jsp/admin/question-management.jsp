<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Question Management Page</title>
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
  <h1>Question Management Page</h1>

  <!-- Add New Question Button -->
  <button onclick="window.location.href='/addQuestion';" class="button">Add New Question</button>

  <!-- Questions Table -->
  <table border="1">
    <tr>
      <th>Id</th>
      <th>Category</th>
      <th>Description</th>
      <th>Actions</th>
    </tr>
    <c:forEach items="${questionList}" var="question">
      <tr>
        <td>${question.id}</td>
        <td>${categoryMap[question.categoryId]}</td>
        <td>${question.description}</td>
        <td>
          <button onclick="window.location.href='/editQuestion/${question.id}';" class="button">Edit</button>
          <form action="/toggleQuestionStatus" method="post" style="display: inline;">
            <input type="hidden" name="questionId" value="${question.id}">
            <input type="submit" class="button" value="${question.active ? 'Suspend' : 'Activate'}">
          </form>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>

</body>
</html>
