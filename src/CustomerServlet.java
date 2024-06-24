import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String allRecod = "";


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade","root","Prabath550@");
            ResultSet rst = connection.prepareStatement("select * from Customer").executeQuery();
            resp.addHeader("ABCD","GAAJI");
            String json="[";
            while (rst.next()) {
                String customer="{";
                String id = rst.getString(1);
                String name = rst.getString(2);
                String address = rst.getString(3);
               double salary = rst.getDouble(4);

                customer+="\"id\":\""+id+"\",";
                customer+="\"name\":\""+name+"\",";
                customer+="\"address\":\""+address+"\",";
                customer+="\"salary\":\""+salary+"\"";

                customer+="},";
                json+=customer;

            }

            resp.addHeader("Content-Type", "application/json");
            json=json+"]";
            resp.getWriter().print(json.substring(0,json.length()-2)+"]");


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     String id=  req.getParameter("customerId");
      String name=  req.getParameter("customerName");
     String add=   req.getParameter("customerAddress");
        String salary=   req.getParameter("customerSalary");

        System.out.println(id+" "+ name+ "  "+add+" "+salary);


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade","root","Prabath550@");
            PreparedStatement pstm = connection.prepareStatement("insert into customer values (?,?,?,?)");
            pstm.setObject(1,id);
            pstm.setObject(2,name);
            pstm.setObject(3,add);
            pstm.setObject(4,salary);
            boolean b=pstm.executeUpdate()>0;
            PrintWriter writer = resp.getWriter();
            if (b){
                writer.write("Customer added..!");
            }


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
