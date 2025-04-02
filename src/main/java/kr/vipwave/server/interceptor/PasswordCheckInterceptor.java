package kr.vipwave.server.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class PasswordCheckInterceptor implements HandlerInterceptor {
    @Value("${admin.password}")
    private String password;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();

        if (!method.equals("PATCH") && !method.equals("DELETE")) {
            return true;
        }

        String provided = request.getHeader("X-ADMIN-CODE");
        if (provided == null || !provided.equals(password)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("비밀번호가 일치하지 않습니다.");
            return false;
        }
        return true;
    }
}
