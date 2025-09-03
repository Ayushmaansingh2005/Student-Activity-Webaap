<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Register | Glassmorphism Form</title>
  <!-- Link to the external CSS file -->
  <link rel="stylesheet" href="<c:url value='/resources/css/register.css' />">
</head>
<body>
  <div class="wrapper">
    <!-- The form action should point to your Spring MVC registration handler -->
    <form action="<c:url value='/handle_register' />" method="post">
      <h2>Register</h2>

      <div class="input-field">
        <input type="text" name="name" required>
        <label>Enter your full name</label>
      </div>

      <div class="input-field">
        <input type="email" name="email" required>
        <label>Enter your email</label>
      </div>

      <div class="input-field">
        <input type="password" name="password" required>
        <label>Enter your password</label>
      </div>

      <div class="input-field">
        <input type="password" name="confirmPassword" required>
        <label>Confirm your password</label>
      </div>

      <button type="submit">Register</button>

      <div class="login-link">
        <p>Already have an account? <a href="<c:url value='/login' />">Login</a></p>
      </div>

      <!-- Show error/success messages from Spring Model -->
      <c:if test="${not empty error}">
        <p class="message error">${error}</p>
      </c:if>
      <c:if test="${not empty success}">
        <p class="message success">${success}</p>
      </c:if>
    </form>
  </div>
</body>
</html>
