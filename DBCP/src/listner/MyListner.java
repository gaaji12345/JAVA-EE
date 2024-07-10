package listner;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;


@WebListener
public class MyListner implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("awa");


        System.out.println("Context Listner");
        BasicDataSource bds=new BasicDataSource();
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        bds.setUrl("jdbc:mysql://localhost:3306/ezypos");
        bds.setUsername("root");
        bds.setPassword("Prabath550@");

        bds.setMaxTotal(5);
        bds.setInitialSize(5);

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("bds",bds);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
        System.out.println("giya");
        ServletContext servletContext = sce.getServletContext();
        BasicDataSource bds= (BasicDataSource) servletContext.getAttribute("bds");

            bds.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
