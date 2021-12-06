package task.manager.service;

import org.springframework.stereotype.Service;
import task.manager.model.Task;

@Service
public interface TaskService {
    void createTask(final Task task);

    void changeTaskStatus(final long taskId);

    boolean hasTaskWithId(final long taskId);

    Task getTaskById(final long taskId);
}
