package StudentActivity.dao;


import StudentActivity.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class StudentDao {


    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void save(Student student){
        this.hibernateTemplate.save(student);
    }

    public Student FindPassword(String email){
        String hql = "FROM Student WHERE email = :email";
        List<Student> studentList = (List<Student>) hibernateTemplate.findByNamedParam(hql,"email",email);
        if(studentList!=null && !studentList.isEmpty()){
            return studentList.get(0);
        }else{
            return null;
        }

    }

}
