package task.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import task.manager.model.Task;
import task.manager.service.TaskService;

@Controller
public class TaskController extends BaseController {
    private final TaskService taskService;

    public TaskController(final TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/task/addTask")
    public String addTask(@ModelAttribute("task") final Task task) {
        taskService.createTask(task);
        return "redirect:/taskList/" + task.getTaskListId();
    }

    @PostMapping("/task/changeTaskStatus")
    public String changeTaskStatus(@ModelAttribute("task") final Task task) {
        long taskId = task.getId();
        if (taskService.hasTaskWithId(taskId)) {
            taskService.changeTaskStatus(taskId);
            long taskListId = taskService.getTaskById(taskId).getTaskListId();
            return "redirect:/taskList/" + taskListId;
        }
        return "redirect:/error";
    }
}
