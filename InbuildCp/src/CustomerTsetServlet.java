import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet(urlPatterns = "/customertest")
public class CustomerTsetServlet extends HttpServlet {

//    @Resource(name = "java:comp/env/jdbc/pool")
//    DataSource dataSource;

    InitialContext ctx;

    {
        try {
            ctx = new InitialContext();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    DataSource ds = (DataSource) ctx.lookup
            ("java:comp/env/jdbc/pool");

    public CustomerTsetServlet() throws NamingException {
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection=ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from customer");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String string = resultSet.getString(1);
                System.out.println(string);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
