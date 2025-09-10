package StudentActivity.dao;
import StudentActivity.Model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class TaskDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void save(Task task){
        this.hibernateTemplate.save(task);
    }


    @Transactional(readOnly = true)
    public List<Task> showTaskById(int studentId) {


        String hql = "FROM Task WHERE student.id = :studentId";
        List<Task> tasks = (List<Task>) hibernateTemplate.findByNamedParam(hql, "studentId", studentId);

        return tasks;
    }



}
