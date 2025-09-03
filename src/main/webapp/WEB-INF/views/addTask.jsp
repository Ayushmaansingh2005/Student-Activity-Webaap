<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Add New Task</title>
  <!-- Link to the external CSS file -->
  <link rel="stylesheet" href="<c:url value='/resources/css/addTask.css' />">
</head>
<body>
  <div class="form-container">
    <div class="form-header">
        <h1>Create a New Task</h1>
        <div class="theme-switch-wrapper">
          <label class="theme-switch" for="checkbox">
            <input type="checkbox" id="checkbox" />
            <div class="slider round"></div>
          </label>
        </div>
    </div>

    <form action="/tasks/add" method="post">
        <div class="form-group">
            <label for="title">Task Title</label>
            <input type="text" id="title" name="title" required placeholder="e.g., Finish Chapter 5 Math Problems">
        </div>
        <div class="form-group">
            <label for="dueDate">Due Date</label>
            <input type="date" id="dueDate" name="dueDate" required>
        </div>

        <div class="form-actions">
            <button type="button" class="form-button secondary" onclick="window.location.href='<c:url value="/activity" />'">Cancel</button>
            <button type="submit" class="form-button primary">Save Task</button>
        </div>
    </form>
  </div>

  <!-- Link to the external JavaScript file at the end of the body -->
  <script src="<c:url value='/resources/js/addTask.js' />"></script>
</body>
</html>
