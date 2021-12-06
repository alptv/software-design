package task.manager.service;

import org.springframework.stereotype.Service;
import task.manager.dao.TaskListDao;
import task.manager.dao.TasksDao;
import task.manager.model.Task;
import task.manager.model.TaskList;

import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {
    private final TasksDao tasksDao;
    private final TaskListDao taskListDao;

    public TaskListServiceImpl(final TasksDao tasksDao, final TaskListDao taskListDao) {
        this.tasksDao = tasksDao;
        this.taskListDao = taskListDao;
    }

    @Override
    public List<Task> getTasksForTaskList(final long taskListId) {
        return tasksDao.findTasksByTaskListId(taskListId);
    }

    @Override
    public TaskList getTaskListById(final long taskListId) {
        return taskListDao.findTaskListById(taskListId).orElse(null);
    }

    @Override
    public boolean hasTaskListWithId(final long taskListId) {
        return getTaskListById(taskListId) != null;
    }

    @Override
    public void deleteTaskListById(final long taskListId) {
        taskListDao.deleteTaskListById(taskListId);
    }
}
