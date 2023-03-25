package product.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.WebSession;
import product.service.model.User;

@Service
public class AuthService {
    public static final String USER_KEY = "user";

    public boolean isAuthenticated(WebSession webSession) {
        return webSession.getAttributes().containsKey(USER_KEY);
    }

    public User getUser(WebSession webSession) {
        return (User) webSession.getAttributes().get(USER_KEY);
    }

    public void logout(WebSession webSession) {
        webSession.getAttributes().remove(USER_KEY);
    }
    
    public void login(User user, WebSession webSession) {
        webSession.getAttributes().put(USER_KEY, user);
    }
}
