package StudentActivity.studentService;


import StudentActivity.Model.SubTask;
import StudentActivity.Model.Task;
import StudentActivity.dao.SubTaskDao;
import StudentActivity.dto.SubTaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubTaskService {

    @Autowired
    private SubTaskDao subTaskDao;


    @Transactional(readOnly = false)
    public void createsubtask(SubTaskDto subTaskDto, Task task){
        SubTask newsubTask = new SubTask();

        newsubTask.setTitle(subTaskDto.getTitle());
        newsubTask.setComplete(subTaskDto.isComplete());
        newsubTask.setTask(task);
        subTaskDao.saveSubTask(newsubTask);

    }

    public List<SubTask> getAllsubTask(int taskId){
        return subTaskDao.getSubTaskById(taskId);
    }
}
