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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Context Listner");
        BasicDataSource bds=new BasicDataSource();
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        bds.setUrl("jdbc:mysql://localhost:3306/ezypos");
        bds.setUsername("root");
        bds.setPassword("Prabath550@");

        bds.setMaxTotal(5);
        bds.setInitialSize(5);

        try {
            Connection connection=bds.getConnection();
            PreparedStatement statement = connection.prepareStatement("select  * from customer");
            ResultSet r=statement.executeQuery();
            while (r.next()){
                String string = r.getString(1);
                System.out.println(string);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
