package StudentActivity.dao;


import StudentActivity.Model.SubTask;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class SubTaskDao {

    @Autowired
    public HibernateTemplate hibernateTemplate;

    public void saveSubTask(SubTask subTask){
        hibernateTemplate.save(subTask);
    }

    @Transactional(readOnly = true)
    public List<SubTask> getSubTaskById(int ParentTaskId){
        String hql = "From SubTask WHERE parent_TaskId = :ParentTaskId " ;
        List<SubTask> subTaskList = (List<SubTask>)hibernateTemplate.findByNamedParam(hql,"ParentTaskId",ParentTaskId);
        return subTaskList;
    }


}
