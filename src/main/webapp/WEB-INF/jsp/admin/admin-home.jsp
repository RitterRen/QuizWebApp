<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Home</title>
    <style>
        .centered-content {
            text-align: center;
            margin: auto;
        }
        h1 {
            margin-bottom: 40px; /* 标题和链接之间的间隔 */
        }
        h2 {
            margin-bottom: 30px; /* 链接之间的间隔 */
        }
        .link-item {
            display: block;
        }
    </style>
</head>
<body>
<jsp:include page="../navbar.jsp" />

<div class="centered-content">
    <h1>Admin Home</h1>
    <h2><a class="link-item" href="/userManagement">User Management Page</a></h2>
    <h2><a class="link-item" href="/quizResultManagement">Quiz Result Management Page</a></h2>
    <h2><a class="link-item" href="/questionManagement">Question Management Page</a></h2>
    <h2><a class="link-item" href="/contactManagement">Contact Us Management Page</a></h2>
</div>

</body>
</html>

