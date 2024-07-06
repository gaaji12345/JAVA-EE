package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {
    public MyFilter() {
        System.out.println("Construtor");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter 1");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("filter 2");


    }

    @Override
    public void destroy() {
        System.out.println("distroy");
    }
}
