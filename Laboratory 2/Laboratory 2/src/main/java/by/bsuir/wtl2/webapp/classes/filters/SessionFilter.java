package by.bsuir.wtl2.webapp.classes.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        if(session.isNew()){
            if(session.isNew()) {
                session.setMaxInactiveInterval(300 * 60);
                session.setAttribute("log", false);
                httpRequest.setAttribute("locale","en");
            }
            session.setAttribute("courses",null);
            session.setAttribute("orders",null);
            session.setAttribute("input_error",null);
            httpResponse.sendRedirect("/pages/main.jsp");
        }
        chain.doFilter(request,response);
    }
}
