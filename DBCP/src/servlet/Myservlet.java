package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/hello")
public class Myservlet extends HttpServlet {

    public   Myservlet(){
        System.out.println("Construtor");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("Init me");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get me");
    }

    @Override
    public void destroy() {
        System.out.println("distroy");
    }
}

