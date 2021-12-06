package task.manager.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class UtilController extends BaseController {
    @GetMapping("/")
    public String getMainPage() {
        return "redirect:/taskList";
    }

    @GetMapping("/error")
    public String error() {
        return "redirect:/taskList";
    }
}
