package StudentActivity.controller;


import StudentActivity.Model.Student;
import StudentActivity.dto.LoginDto;
import StudentActivity.dto.RegistratioDto;
import StudentActivity.studentService.LoginService;
import StudentActivity.studentService.RegistrationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {
    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private LoginService loginService;

    @RequestMapping({"/", "/homePage"})
    public String index() {
        return "index"; // resolves to /WEB-INF/views/index.jsp
    }


    @RequestMapping("/register")
    public String registerPage(){
        return "register";
    }


    @RequestMapping(value = "handle_register",method = RequestMethod.POST)
    public String handleRegistration(
            @ModelAttribute RegistratioDto registratioDto,
            Model model){

                        if(!registratioDto.getPassword().equals(registratioDto.getConfirmPassword())){
                            model.addAttribute("error","Please try again !!!");
                            return "register";
                        }


                        Student student = new Student();
                        student.setName(registratioDto.getName());
                        student.setEmail(registratioDto.getEmail());

                        String hashedpassword = passwordEncoder.encode(registratioDto.getPassword());
                        student.setPassword(hashedpassword);
                        model.addAttribute("success","Registration successfull !!!");
                        System.out.println(student);
                        registrationService.AddDetails(student);
                        return "index";

    }


    @RequestMapping(value = "handle_login",method = RequestMethod.POST)
    public String handleLogin(@ModelAttribute LoginDto loginDto , Model model){
        if(loginService.login(loginDto)){
            return "activity";
        }
        model.addAttribute("error","Wrong email or password try again !!!");
        return "index";

    }

    @RequestMapping("/activity")
    public String activityCenter(){
        return "activity";
    }
//
//    @RequestMapping("/addtask")
//    public String addTask(){
//        return "addTask";
//    }


}
