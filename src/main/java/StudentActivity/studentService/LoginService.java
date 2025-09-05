package StudentActivity.studentService;

import StudentActivity.Model.Student;
import StudentActivity.dao.StudentDao;
import StudentActivity.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Student login(LoginDto logindto){

        Student originalstudent = this.studentDao.FindPassword(logindto.getEmail());

        if(originalstudent!=null && passwordEncoder.matches( logindto.getPassword(),originalstudent.getPassword())){
            return originalstudent;
        }

        return null;
    }


}
