package StudentActivity.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int taskId;
    private String task;
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name="student_id",referencedColumnName = "id")
    private Student student;


    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Task(int taskId, String task, Date date, Student student) {
        this.taskId = taskId;
        this.task = task;
        this.date = date;
        this.student = student;
    }

    public Task() {
        super();
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", task='" + task + '\'' +
                ", date=" + date +
                ", student=" + student +
                '}';
    }
}
