package StudentActivity.dao;
import StudentActivity.Model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TaskDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void save(Task task){
        this.hibernateTemplate.save(task);
    }


}
