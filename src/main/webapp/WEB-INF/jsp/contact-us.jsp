<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Contact Us</title>
    <style>
        .centered-content {
            text-align: center;
            margin: auto;
            width: 50%;
        }
        .form-field {
            margin-bottom: 20px;
        }
        .form-label {
            display: block;
            margin-bottom: 5px;
        }
        .form-input, .form-textarea {
            width: 100%;
            padding: 8px;
        }
        .form-textarea {
            height: 100px;
        }
        .submit-button {
            display: block;
            margin: 20px auto;
            padding: 8px 16px;
        }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="centered-content">
    <h2>Contact Us</h2>
    <form action="/contact" method="post">
        <div class="form-field">
            <label class="form-label" for="subject">Subject:</label>
            <input class="form-input" type="text" id="subject" name="subject">
        </div>
        <div class="form-field">
            <label class="form-label" for="email">Email Address:</label>
            <input class="form-input" type="email" id="email" name="email">
        </div>
        <div class="form-field">
            <label class="form-label" for="message">Message:</label>
            <textarea class="form-textarea" id="message" name="message"></textarea>
        </div>
        <input class="submit-button" type="submit" value="Send Message">
    </form>
</div>

</body>
</html>
