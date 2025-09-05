package StudentActivity.controller;

import StudentActivity.Model.Student;
import StudentActivity.dto.TaskDto;
import StudentActivity.studentService.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TaskController {


    @Autowired
    private TaskService taskService;

    @RequestMapping("/activity")
    public String activityCenter(){
        return "activity";
    }

    @RequestMapping("/addTask")
    public String addTask(){
        return "addTask";
    }

    @RequestMapping(value="handle_addTask",method = RequestMethod.POST)
    public String newTask(@ModelAttribute TaskDto taskDto, HttpSession session){
        Student currentStudent = (Student) session.getAttribute("currentUser");

        if(currentStudent==null){
            return "index";
        }
        taskService.createtask(currentStudent,taskDto);
        return "activity";
    }
}
