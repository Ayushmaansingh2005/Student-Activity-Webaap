package StudentActivity.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TaskDto {

    private String task;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

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
}
