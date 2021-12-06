package task.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import task.manager.model.User;
import task.manager.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController  extends BaseController {
    private final UserService userService;

    public AuthController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(HttpSession httpSession, @ModelAttribute("userCredentials") User userCredentials) {
        if (userService.isLoginFree(userCredentials.getLogin())) {
            User user = userService.register(userCredentials);
            setUser(httpSession, user);
            return "redirect:/";
        } else {
            return "redirect:/auth";
        }
    }

    @GetMapping("/auth")
    public String showEnter(HttpSession httpSession) {
        if (isLoggedIn(httpSession)) {
            return "redirect:/";
        }
        return "auth";
    }

    @PostMapping("/enter")
    public String enter(HttpSession httpSession, @ModelAttribute("userCredentials") User userCredentials) {
        if (userService.isValidUser(userCredentials)) {
            User user = userService.findUserByLogin(userCredentials.getLogin());
            setUser(httpSession, user);
            return "redirect:/";
        } else {
            return "redirect:/auth";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        unsetUser(httpSession);
        return "redirect:/";
    }
}
