package task.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import task.manager.model.User;

import javax.servlet.http.HttpSession;

@Controller
public class BaseController {
    private static final String USER_ID_SESSION_KEY = "userId";

    public void setUser(HttpSession httpSession, User user) {
        httpSession.setAttribute(USER_ID_SESSION_KEY, user);
    }

    public void unsetUser(HttpSession httpSession) {
        httpSession.removeAttribute(USER_ID_SESSION_KEY);
    }

    @ModelAttribute("user")
    public User getUser(HttpSession httpSession) {
        Object user = httpSession.getAttribute(USER_ID_SESSION_KEY);
        return user == null ? null : (User) user;
    }

    public boolean isLoggedIn(HttpSession httpSession) {
        return httpSession.getAttribute(USER_ID_SESSION_KEY) != null;
    }
}
