package task.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import task.manager.model.TaskList;
import task.manager.service.TaskListService;
import task.manager.service.UserService;

import javax.servlet.http.HttpSession;


@Controller
public class TasksListController extends BaseController {
    private final TaskListService taskListService;
    private final UserService userService;

    public TasksListController(final TaskListService taskListService, final UserService userService) {
        this.taskListService = taskListService;
        this.userService = userService;
    }


    @GetMapping("/taskList")
    public String getAllTaskLists(final HttpSession httpSession, final Model model) {
        long userId = getUser(httpSession).getId();
        model.addAttribute("taskLists", userService.getTaskListForUser(userId));
        return "taskLists";
    }


    @GetMapping("/taskList/{taskListId}")
    public String getTaskList(final Model model, @PathVariable final String taskListId) {
        long id;
        try {
            id = Long.parseLong(taskListId);
        } catch (NumberFormatException e) {
            return "redirect:/error";
        }
        if (taskListService.hasTaskListWithId(id)) {
            TaskList taskList = taskListService.getTaskListById(id);
            model.addAttribute("taskList", taskList);
            model.addAttribute("tasks", taskListService.getTasksForTaskList(id));
            return "tasks";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/taskList/createTaskList")
    public String createTaskList(HttpSession session, @ModelAttribute("taskList") final TaskList taskList) {
        userService.createTaskList(getUser(session).getId(), taskList);
        return "redirect:/taskList";
    }

    @PostMapping("/taskList/deleteTaskList")
    public String deleteTaskList(@ModelAttribute("taskList") final TaskList taskList) {
        taskListService.deleteTaskListById(taskList.getId());
        return "redirect:/taskList";
    }

}
