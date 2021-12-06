package task.manager.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import task.manager.model.TaskList;

import java.util.List;
import java.util.Optional;

public class TaskListJdbcDao extends JdbcDaoSupport implements TaskListDao {
    @Override
    public Optional<TaskList> findTaskListById(final long taskListId) {
        String sql = "select Id, Name, UserId from TasksLists where Id = ?";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(TaskList.class), taskListId).stream().findFirst();
    }

    @Override
    public List<TaskList> findTaskListsByUserId(final long userId) {
        String sql = "select Id, Name, UserId from TasksLists where UserId = ?";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(TaskList.class), userId);
    }

    @Override
    public void saveTaskList(final TaskList taskList) {
        String sql = "insert into TasksLists (Name, UserId) values (?, ?)";
        getJdbcTemplate().update(sql, taskList.getName(), taskList.getUserId());
    }

    @Override
    public void deleteTaskListById(final long taskListId) {
        String sql = "delete from TasksLists where Id = ?";
        getJdbcTemplate().update(sql, taskListId);
    }
}
