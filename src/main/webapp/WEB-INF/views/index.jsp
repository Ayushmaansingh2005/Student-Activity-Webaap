<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Glassmorphism Login Form</title>
  <!-- Link to the external CSS file -->
  <link rel="stylesheet" href="<c:url value='/resources/css/login.css' />">
</head>
<body>
  <div class="wrapper">
    <!-- The form action should point to your Spring MVC handler -->
    <form action="<c:url value='/handle_login' />" method="post">
      <h2>Login</h2>

      <div class="input-field">
        <input type="text" name="email" required>
        <label>Enter your email</label>
      </div>

      <div class="input-field">
        <input type="password" name="password" required>
        <label>Enter your password</label>
      </div>

      <div class="forget">
        <label for="remember">
          <input type="checkbox" id="remember" name="remember">
          <p>Remember me</p>
        </label>
        <a href="#">Forgot password?</a>
      </div>

      <button type="submit">Log In</button>

      <div class="register">
        <p>Don't have an account? <a href="<c:url value='/register' />">Register</a></p>
      </div>

      <!-- Show login error if Spring adds "error" to the model -->
      <c:if test="${not empty error}">
        <p class="error-message">${error}</p>
      </c:if>
    </form>
  </div>
</body>
</html>
