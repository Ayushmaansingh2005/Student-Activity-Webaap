<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student Activity Tracker</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <link rel="stylesheet" href="<c:url value='/resources/css/activity.css' />">
</head>
<body>
    <div class="main-container">
        <div class="header">
            <div class="productivity-chart-container"><canvas id="productivityDoughnutChart"></canvas></div>
            <div class="header-right">
                <div class="badge">🏆 Platinum Badge</div>
                <div class="theme-switch-wrapper">
                    <label class="theme-switch" for="checkbox"><input type="checkbox" id="checkbox" /><div class="slider round"></div></label>
                </div>
            </div>
        </div>

        <div class="tasks-section">
            <c:forEach var="task" items="${tasks}">
                <%--
                    Step 1: Format dates and store them in page-scoped variables.
                    This is the most reliable way to avoid JSP engine parsing issues.
                --%>
                <fmt:formatDate value="${task.creationDate}" pattern="yyyy-MM-dd" var="formattedCreationDate" />
                <fmt:formatDate value="${task.date}" pattern="yyyy-MM-dd" var="formattedDueDate" />

                <%--
                    Step 2: Use the simple variables in the data attributes.
                --%>
                <div class="task-card"
                     data-task-id="${task.taskId}"
                     data-creation-date="${formattedCreationDate}"
                     data-due-date="${formattedDueDate}">

                    <div class="task-header">
                        <span class="task-title"><c:out value="${task.task}"/></span>
                        <span class="days-remaining"></span>
                        <span class="task-toggle-icon collapsed">&#9662;</span>
                    </div>

                    <div class="progress-container">
                        <div class="progress-bar" style="width: 0%;"></div>
                    </div>

                    <p class="deadline">Deadline:
                       <fmt:formatDate value="${task.date}" pattern="dd MMM yyyy" />
                    </p>

                    <div class="subtasks-section hidden">
                        <div class="subtasks-header">Subtasks (0 / 0)</div>
                        <div class="progress-container"><div class="progress-bar" style="width: 0%;"></div></div>
                        <div class="subtasks-list">
                            <%-- This section can be populated with a nested loop if you add subtasks --%>
                        </div>
                        <div class="add-subtask-form">
                            <input type="text" class="add-subtask-input" placeholder="Add a new subtask...">
                            <button type="button" class="add-subtask-button">Add</button>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <button class="add-task-btn" onclick="window.location.href='<c:url value='/addTask' />'">+ Add New Task</button>
        </div>

        <%-- The rest of your page layout --%>
        <div class="streak-section">
            <h2>Yearly Activity Streak</h2>
            <div class="streak-grid">
                <c:forEach var="month" items="${['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']}" varStatus="loop">
                    <div class="month-streak">
                        <h3>${month}</h3>
                        <div class="calendar" id="calendar-${loop.index}"></div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="yearly-graph-section">
            <h2>Yearly Productivity</h2>
            <canvas id="yearlyProductivityChart"></canvas>
        </div>
        <div class="completed-tasks-section">
            <h2>Completed Tasks</h2>
            <table class="task-table">
                <thead>
                    <tr><th>No.</th><th>Task Description</th><th>Date Completed</th></tr>
                </thead>
                <tbody>
                    <%-- This would also be populated dynamically from your data --%>
                </tbody>
            </table>
        </div>
    </div>

    <%-- Link to your external JavaScript file at the end of the body --%>
    <script src="<c:url value='/resources/js/activity.js' />"></script>
</body>
</html>
