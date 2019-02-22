package by.artezio.cloud.publishing.util.filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(value = "/*")
public class AuthenticationFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(AuthenticationFilter.class.getName());

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        LOG.info("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        LOG.log(Level.INFO, "Requested Resource::{0}", uri);

        HttpSession session = req.getSession(false);

        if (session == null && !(uri.endsWith("jsp") || uri.endsWith("login"))) {
            LOG.info("Unauthorized access request");
            res.sendRedirect("login");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
