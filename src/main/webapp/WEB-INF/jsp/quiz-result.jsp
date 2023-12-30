<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Quiz Result</title>
  <style>
    .centered {
      text-align: center;
    }
    .questionResults {
      margin-top: 40px;
    }
    .questionResult {
      margin: 20px auto;
      width: 60%;
    }
  </style>
</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="centered">
  <h2>Quiz Result</h2>
  <!-- Display Quiz and User Information -->
  <p><strong>Quiz Name: </strong> ${quizName}</p>
  <p><strong>User Name: </strong> ${userName}</p>
  <p><strong>Quiz Start Time: </strong> ${quizStartTime}</p>
  <p><strong>Quiz End Time: </strong> ${quizEndTime}</p>
  <p><strong>Result: </strong> ${testResult}</p>

  <!-- Display Detailed Results for Each Question -->
  <h3 class="questionResults">Question Details</h3>
  <div class="centered">
    <c:forEach var="qr" items="${questionResultList}">
      <div class="questionResult" style="background-color: ${qr.userSelectedOption.id == qr.correctOption.id ? 'lightgreen' : 'lightgrey'};">
        <p>Question: ${qr.question.description}</p>
        <p>Options: </p>
        <ul style="list-style-type: disc; margin-left: 20px;">
          <c:forEach var="option" items="${qr.questionOptions}">
            <li>${option.description}</li>
          </c:forEach>
        </ul>
        <p>Your Answer: ${qr.userSelectedOption.description}</p>
        <p>Correct Answer: ${qr.correctOption.description}</p>
      </div>
    </c:forEach>
  </div>

  <!-- Link to Take Another Quiz -->
  <a href="/home">Take Another Quiz</a>
</div>

</body>
</html>
