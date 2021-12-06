package task.manager.security;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import task.manager.controller.AuthController;
import task.manager.controller.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

@Component
public class SecurityInterceptor implements HandlerInterceptor {
    private final BaseController baseController;

    public SecurityInterceptor(final BaseController baseController) {
        this.baseController = baseController;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        if (baseController.isLoggedIn(request.getSession())) {
            return true;
        }
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            if (!method.getDeclaringClass().equals(AuthController.class) ||
                    method.equals(AuthController.class.getMethod("logout", HttpSession.class))) {
                response.sendRedirect("/auth");
                return false;
            }
        }
        return true;
    }
}
