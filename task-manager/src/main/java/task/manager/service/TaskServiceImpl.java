package task.manager.service;

import org.springframework.stereotype.Service;
import task.manager.dao.TasksDao;
import task.manager.model.Task;

@Service
public class TaskServiceImpl implements TaskService {
    private final TasksDao tasksDao;

    public TaskServiceImpl(final TasksDao tasksDao) {
        this.tasksDao = tasksDao;
    }

    @Override
    public void createTask(final Task task) {
        task.setDone(false);
        tasksDao.saveTask(task);
    }

    @Override
    public void changeTaskStatus(final long taskId) {
        Task task = getTaskById(taskId);
        if (task != null) {
            task.setDone(!task.getDone());
            tasksDao.updateTask(task);
        }
    }

    @Override
    public boolean hasTaskWithId(final long taskId) {
        return tasksDao.findTaskById(taskId).isPresent();
    }

    @Override
    public Task getTaskById(final long taskId) {
        return tasksDao.findTaskById(taskId).orElse(null);
    }
}
