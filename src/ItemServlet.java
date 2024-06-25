import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String allRecod = "";

        try {
            resp.setContentType("application/json");
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade","root","Prabath550@");
            ResultSet rst = connection.prepareStatement("select * from Item").executeQuery();
//            resp.addHeader("ABCD","GAAJI");
            String json="[";
            while (rst.next()) {
                String item="{";
                String code = rst.getString(1);
                String  description  = rst.getString(2);
                double price = rst.getDouble(3);
                int qty =rst.getInt(4);

                item+="\"code\":\""+code+"\",";
                item+="\" description \":\""+ description +"\",";
                item+="\"price\":\""+price+"\",";
                item+="\"qty\":\""+qty+"\"";

                item+="},";
                json+=item;

            }

//            resp.addHeader("Content-Type", "application/json");
            json=json+"]";
            resp.getWriter().print(json.substring(0,json.length()-2)+"]");


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
