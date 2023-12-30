<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Quiz</title>
    <style>
        .centered-content {
            text-align: center;
        }
        .question-container {
            margin: auto;
            width: 80%;
        }
        .question {
            background-color: lightgray; /* 未回答问题的浅灰色背景 */
            margin: 10px;
            padding: 10px;
        }
        .answered {
            background-color: darkgray; /* 已回答问题的深灰色背景 */
        }
        .choice {
            margin-left: 20px;
        }
        .submit-button {
            display: block;
            margin: 20px auto; /* 居中显示提交按钮 */
        }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp" />

<h2 class="centered-content">${quizName}</h2>
<form action="/submitQuiz" method="post">
    <div class="question-container">
        <c:forEach var="question" items="${questionChoiceMap.keySet()}" varStatus="status">
            <div id="question-${status.index}" class="question">
                <p>${question.description}</p>
                <c:forEach var="choice" items="${questionChoiceMap[question]}">
                    <div class="choice">
                        <input type="radio" name="${question.id}" value="${choice.id}" onclick="markAnswered(${status.index})">${choice.description}
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
    </div>
    <input type="submit" value="Submit Quiz" class="submit-button">
</form>

<script>
    function markAnswered(questionIndex) {
        var element = document.getElementById('question-' + questionIndex);
        if(element) {
            element.classList.add('answered');
        }
    }
</script>
</body>
</html>
