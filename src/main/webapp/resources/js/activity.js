document.addEventListener('DOMContentLoaded', () => {
    // --- Universal Theme Toggle (for all pages) ---
    const toggleSwitch = document.querySelector('.theme-switch input[type="checkbox"]');
    if (toggleSwitch) {
        const currentTheme = localStorage.getItem('theme');
        if (currentTheme) {
            document.body.classList.add(currentTheme);
            if (currentTheme === 'dark-mode') {
                toggleSwitch.checked = true;
            }
        }
        function switchTheme(e) {
            if (e.target.checked) {
                document.body.classList.add('dark-mode');
                localStorage.setItem('theme', 'dark-mode');
            } else {
                document.body.classList.remove('dark-mode');
                localStorage.setItem('theme', 'light');
            }
        }
        toggleSwitch.addEventListener('change', switchTheme, false);
    }

    // --- Page-Specific Logic for activity.jsp ---
    const activityPageIdentifier = document.getElementById('productivityDoughnutChart');
    if (activityPageIdentifier) {

        console.log("Activity page script running."); // For debugging

        const taskCards = document.querySelectorAll('.task-card');
        console.log(`Found ${taskCards.length} task cards to process.`); // For debugging

        taskCards.forEach((card, index) => {
            const creationDateString = card.dataset.creationDate;
            const dueDateString = card.dataset.dueDate;
            const daysRemainingSpan = card.querySelector('.days-remaining');
            const progressBar = card.querySelector('.progress-bar');

            // --- Step 1: Robust Date Parsing ---
            // The 'YYYY-MM-DD' format is not universally supported by new Date().
            // Replacing hyphens with slashes makes it cross-browser compatible.
            const creationDate = new Date(creationDateString.replace(/-/g, '/'));
            const dueDate = new Date(dueDateString.replace(/-/g, '/'));
            const today = new Date();
            today.setHours(0, 0, 0, 0); // Normalize today's date for accurate day comparison

            // --- Step 2: Days Remaining Calculation ---
            if (daysRemainingSpan && !isNaN(dueDate)) {
                const differenceInMs = dueDate.getTime() - today.getTime();
                const differenceInDays = Math.ceil(differenceInMs / (1000 * 60 * 60 * 24));

                if (differenceInDays < 0) {
                    daysRemainingSpan.textContent = 'Overdue';
                    daysRemainingSpan.style.color = 'red';
                } else if (differenceInDays === 0) {
                    daysRemainingSpan.textContent = 'Due today';
                } else if (differenceInDays === 1) {
                    daysRemainingSpan.textContent = '1 day left';
                } else {
                    daysRemainingSpan.textContent = `${differenceInDays} days left`;
                }
            }

            // --- Step 3: Progress Bar Calculation ---
            if (progressBar && !isNaN(creationDate) && !isNaN(dueDate)) {
                const totalDuration = dueDate.getTime() - creationDate.getTime();
                const elapsedDuration = today.getTime() - creationDate.getTime();

                let progressPercent = 0;
                if (totalDuration > 0) { // Avoid division by zero
                    progressPercent = (elapsedDuration / totalDuration) * 100;
                }

                // Clamp the value between 0 and 100
                progressPercent = Math.min(100, Math.max(0, progressPercent));

                progressBar.style.width = progressPercent + '%';
            } else {
                 console.log(`Skipping progress bar for Task #${index} due to invalid dates.`); // For debugging
            }
        });

        // --- Expand/Collapse Subtasks ---
        document.querySelectorAll('.task-header').forEach(header => {
            header.addEventListener('click', (event) => {
                if (event.target.classList.contains('days-remaining')) return;
                const subtasksSection = header.parentElement.querySelector('.subtasks-section');
                const icon = header.querySelector('.task-toggle-icon');
                if (subtasksSection) {
                    subtasksSection.classList.toggle('hidden');
                    icon.classList.toggle('collapsed');
                }
            });
        });

        // --- Add Subtask Logic ---
        document.querySelectorAll('.add-subtask-button').forEach(button => {
            button.addEventListener('click', () => {
                const form = button.closest('.add-subtask-form');
                const input = form.querySelector('.add-subtask-input');
                const subtasksList = form.closest('.subtasks-section').querySelector('.subtasks-list');
                const subtaskText = input.value.trim();
                if (subtaskText) {
                    const newSubtask = document.createElement('div');
                    newSubtask.classList.add('subtask');
                    newSubtask.innerHTML = `<input type="checkbox"><label>${subtaskText}</label>`;
                    subtasksList.appendChild(newSubtask);
                    input.value = '';
                }
            });
        });

        // --- Streak Calendar Logic ---
        const yearlyActivityData = { 0: new Set([5, 6, 10]), 8: new Set([1, 2, 3]) };
        function generateCalendars() {
            const year = 2025;
            for (let month = 0; month < 12; month++) {
                const calendarEl = document.getElementById(`calendar-${month}`);
                if (!calendarEl) continue;
                const daysInMonth = new Date(year, month + 1, 0).getDate();
                const firstDayOfMonth = new Date(year, month, 1).getDay();
                const activeDays = yearlyActivityData[month] || new Set();
                calendarEl.innerHTML = '';
                for (let i = 0; i < firstDayOfMonth; i++) {
                    const emptyDiv = document.createElement('div');
                    emptyDiv.style.background = 'transparent';
                    calendarEl.appendChild(emptyDiv);
                }
                for (let day = 1; day <= daysInMonth; day++) {
                    const dayEl = document.createElement('div');
                    dayEl.classList.add('day');
                    if (activeDays.has(day)) { dayEl.classList.add('active'); }
                    calendarEl.appendChild(dayEl);
                }
            }
        }
        generateCalendars();

        // --- Chart Logic ---
        const doughnutText = {
          id: 'doughnutText',
          afterDraw(chart) {
            const { ctx, data } = chart;
            const text = data.datasets[0].data[0] + '%';
            const { x, y } = chart.getDatasetMeta(0).data[0];
            ctx.save();
            ctx.font = 'bold 30px Open Sans';
            ctx.fillStyle = getComputedStyle(document.body).getPropertyValue('--text-color');
            ctx.textAlign = 'center';
            ctx.textBaseline = 'middle';
            ctx.fillText(text, x, y);
            ctx.restore();
          }
        };
        new Chart(doughnutChartCanvas, { type: 'doughnut', data: { datasets: [{ data: [75, 25], backgroundColor: ['#4caf50', 'rgba(224, 224, 224, 0.5)'], borderWidth: 0 }] }, options: { responsive: true, cutout: '70%', plugins: { legend: { display: false }, tooltip: { enabled: true }}}, plugins: [doughnutText] });

        const lineChartCanvas = document.getElementById('yearlyProductivityChart');
        new Chart(lineChartCanvas, { type: 'line', data: { labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'], datasets: [{ label: 'Productivity', data: [65, 59, 80, 81, 56, 55, 40, 60, 75, 90, 85, 95], fill: true, backgroundColor: 'rgba(33, 150, 243, 0.2)', borderColor: 'rgba(33, 150, 243, 1)', tension: 0.4 }] }, options: { scales: { y: { beginAtZero: true }}, plugins: { legend: { display: false }}, interaction: { mode: 'index', intersect: false }} });
    }
});
