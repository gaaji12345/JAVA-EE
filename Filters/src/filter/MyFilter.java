package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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


  
        System.out.println("filter 2");
        HttpServletResponse servletResponse1=(HttpServletResponse) servletResponse;

        servletResponse1.addHeader("Gaaji","Broo");
        servletResponse1.addHeader("Access-Control-Allow-Origin","*");//
        servletResponse1.addHeader("Access-Control-Allow-Methods","DELETE, PUT");
        servletResponse1.addHeader("Access-Control-Allow-Headers","Content-Type");

        filterChain.doFilter(servletRequest,servletResponse); // meka ntnm rq eka aphu harenwa

    }

    @Override
    public void destroy() {
        System.out.println("distroy");
    }
}
