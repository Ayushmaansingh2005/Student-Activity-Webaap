package StudentActivity.studentService;

import StudentActivity.Model.Student;
import StudentActivity.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    @Autowired
    private StudentDao studentDao;

    @Transactional(readOnly = false)
    public void AddDetails( Student student){
        this.studentDao.save(student);
    }
}
