package StudentActivity.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping({"/", "/homePage"})
    public String index() {
        return "index"; // resolves to /WEB-INF/views/index.jsp
    }


    @RequestMapping("/register")
    public String registerPage(){
        return "register";
    }

    @RequestMapping("/activity")
    public String activityCenter(){
        return "activity";
    }

    @RequestMapping("/addtask")
    public String addTask(){
        return "addTask";
    }


}
