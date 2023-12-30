<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Home</title>
    <style>
        .centered-content {
            text-align: center;
        }
        .category-list {
            list-style-type: none;
            padding: 0;
            display: flex;
            justify-content: center;
            margin-bottom: 50px;
        }
        .category-list li {
            margin: 0 10px;
        }
        .category-list a {
            text-decoration: none;
            color: #000;
        }
        .table-container {
            margin-top: 20px;
        }
        .category-item {
            margin-bottom: 20px; /* 增加列表项间的间距 */
        }

        .category-item a {
            font-size: 20px; /* 调整字号 */
            font-weight: bold; /* 加粗字体 */
            color: grey;
        }
        .table-container table {
            width: 80%; /* 增加表格宽度 */
        }

        .table-container td {
            padding: 10px; /* 增加单元格大小 */
            text-align: left; /* 设置单元格内文字靠左对齐 */
        }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="centered-content">
    <h2>Quiz Categories</h2>
    <ul class="category-list">
        <c:forEach items="${categoryList}" var="category">
            <li class="category-item">
                <a href="/quiz/${category.id}">${category.name}</a>
            </li>
        </c:forEach>
    </ul>

    <div class="table-container">
        <h2>Recent Quiz Results</h2>
        <table border="1" style="margin-left: auto; margin-right: auto;">
            <tr>
                <th>Quiz Name</th>
                <th>Date Taken</th>
                <th>Result Link</th>
            </tr>
            <c:forEach items="${recentQuizResultList}" var="quizResult">
                <tr>
                    <td>${quizResult.name}</td>
                    <td>${quizResult.dateTaken}</td>
                    <td><a href="/quizResult/${quizResult.quizId}">View Result</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>

