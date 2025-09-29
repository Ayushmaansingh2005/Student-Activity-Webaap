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
    private Date creationDate;

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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Task(int taskId, String task, Date date, Date creationDate, Student student) {
        this.taskId = taskId;
        this.task = task;
        this.date = date;
        this.creationDate = creationDate;
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
                ", creationDate=" + creationDate +
                ", student=" + student +
                '}';
    }
}
