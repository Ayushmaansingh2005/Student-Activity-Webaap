package StudentActivity.studentService;

import StudentActivity.Model.Student;
import StudentActivity.Model.Task;
import StudentActivity.dao.TaskDao;
import StudentActivity.dto.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {


    @Autowired
    private TaskDao taskDao;


    @Transactional(readOnly = false)
    public void createtask(Student student, TaskDto taskDto){
        Task newTask = new Task();
        newTask.setTask(taskDto.getTask());
        newTask.setDate(taskDto.getDate());
        newTask.setCreationDate(new Date());
        newTask.setStudent(student);
        taskDao.save(newTask);
    }

    public List<Task> showTask(int studentId){
        return taskDao.showTaskById(studentId);
    }


}
