package StudentActivity.Model;


import jakarta.persistence.*;
import net.bytebuddy.agent.builder.AgentBuilder;

@Entity
public class SubTask {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int subTaskId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_TaskId",referencedColumnName = "taskId")
    private Task task;

    private String title;

    private  boolean isComplete;

    public int getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(int subTaskId) {
        this.subTaskId = subTaskId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public SubTask() {
        super();
    }

    public SubTask(int subTaskId, Task task, String title, boolean isComplete) {
        this.subTaskId = subTaskId;
        this.task = task;
        this.title = title;
        this.isComplete = isComplete;
    }
}
