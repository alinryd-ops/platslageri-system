import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PinAuthFilter implements Filter {

    @Value("${app.pin}")
    private String correctPin;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();

        // Tillåt health checks (Render behöver detta)
        if (path.startsWith("/actuator")) {
            chain.doFilter(request, response);
            return;
        }

        String pin = req.getHeader("X-PIN");

        if (pin == null || !pin.equals(correctPin)) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Fel PIN");
            return;
        }

        chain.doFilter(request, response);
    }
}