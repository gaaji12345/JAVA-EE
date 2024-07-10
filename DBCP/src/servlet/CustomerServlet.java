package servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("hiiii init customer");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        BasicDataSource bds= (BasicDataSource) servletContext.getAttribute("bds");

        try {
            Connection connection=bds.getConnection();
            PreparedStatement statement = connection.prepareStatement("select  * from customer");
            ResultSet r=statement.executeQuery();
            while (r.next()){
                String string = r.getString(1);
                System.out.println(string);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        BasicDataSource bds= (BasicDataSource) servletContext.getAttribute("bds");
        try {
            Connection connection=bds.getConnection();
            PreparedStatement statement = connection.prepareStatement("select  * from customer");
            ResultSet r=statement.executeQuery();
            while (r.next()){
                String string = r.getString(1);
                System.out.println(string);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
