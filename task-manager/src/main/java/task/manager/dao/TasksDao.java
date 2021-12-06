package task.manager.dao;

import org.springframework.stereotype.Component;
import task.manager.model.Task;

import java.util.List;
import java.util.Optional;

@Component
public interface TasksDao {
    List<Task> findTasksByTaskListId(final long taskListId);

    void saveTask(final Task task);

    Optional<Task> findTaskById(final long taskId);

    void updateTask(final Task task);

}
