package task.manager.service;

import org.springframework.stereotype.Service;
import task.manager.model.Task;
import task.manager.model.TaskList;

import java.util.List;

@Service
public interface TaskListService {
    List<Task> getTasksForTaskList(final long taskListId);

    TaskList getTaskListById(final long taskListId);

    boolean hasTaskListWithId(final long taskListId);

    void deleteTaskListById(final long taskListId);
}
