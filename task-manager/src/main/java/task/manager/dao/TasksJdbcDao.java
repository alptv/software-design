package task.manager.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import task.manager.model.Task;

import java.util.List;
import java.util.Optional;

public class TasksJdbcDao extends JdbcDaoSupport implements TasksDao {
    @Override
    public List<Task> findTasksByTaskListId(final long taskListId) {
        String sql = "select Id, Name, Description, TaskListId, Done from Tasks where TaskListId = ?";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(Task.class), taskListId);
    }

    @Override
    public void saveTask(final Task task) {
        String sql = "insert into Tasks (Name, Description, TaskListId, Done) values (?, ?, ?, ?)";
        getJdbcTemplate().update(sql, task.getName(), task.getDescription(), task.getTaskListId(), task.getDone());
    }

    @Override
    public Optional<Task> findTaskById(final long taskId) {
        String sql = "select Id, Name, Description, TaskListId, Done from Tasks where Id = ?";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(Task.class), taskId).stream().findFirst();
    }

    @Override
    public void updateTask(final Task task) {
        String sql = "update Tasks set Name = ?, Description = ?, TaskListId = ? , Done = ? where Id = ?";
        getJdbcTemplate().update(sql, task.getName(), task.getDescription(), task.getTaskListId(), task.getDone(), task.getId());
    }
}
