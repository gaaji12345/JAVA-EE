import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         resp.setContentType("application/json");


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ezypos", "root", "Prabath550@");
            ResultSet rst = connection.prepareStatement("select * from Customer").executeQuery();
            resp.addHeader("ABCD", "GAAJI");

            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();



            while (rst.next()) {
                String customer = "{";
                String id = rst.getString(1);
                String name = rst.getString(2);
                String address = rst.getString(3);
                double salary = rst.getDouble(4);

                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                     objectBuilder.add("id", id);
                objectBuilder.add("name", name);
                objectBuilder.add("address", address);
                objectBuilder.add("salary", salary);

                //addd to json array
                arrayBuilder.add(objectBuilder.build());

            }

            PrintWriter writer = resp.getWriter();
//            writer.print(arrayBuilder.build());

            JsonObjectBuilder respo = Json.createObjectBuilder();
            respo.add("code",200);
            respo.add("message","Saved");
            respo.add("data",arrayBuilder.build());

            writer.print(respo.build());


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("customerId");
        String name = req.getParameter("customerName");
        String add = req.getParameter("customerAddress");
        String salary = req.getParameter("customerSalary");

        resp.setContentType("application/json");

        System.out.println(id + " " + name + "  " + add + " " + salary);
        PrintWriter writer = resp.getWriter();



        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ezypos", "root", "Prabath550@");
            PreparedStatement pstm = connection.prepareStatement("insert into customer values (?,?,?,?)");
            pstm.setObject(1, id);
            pstm.setObject(2, name);
            pstm.setObject(3, add);
            pstm.setObject(4, salary);
            boolean b = pstm.executeUpdate() > 0;

            if (b) {
                JsonObjectBuilder respo = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_CREATED);
                respo.add("code",200);
                respo.add("message","Saved");
                respo.add("data","");

                writer.print(respo.build());
            }


        } catch (ClassNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_OK);

            JsonObjectBuilder respo = Json.createObjectBuilder();

            respo.add("code",400);
            respo.add("message","Erorr");
            respo.add("data",e.getLocalizedMessage());


            writer.print(respo.build());

            e.printStackTrace();

        } catch (SQLException e) {

            JsonObjectBuilder respo = Json.createObjectBuilder();

            respo.add("code",400);
            respo.add("message","EROOR");
            respo.add("data",e.getLocalizedMessage());

            writer.print(respo.build());
            resp.setStatus(HttpServletResponse.SC_OK);
            e.printStackTrace();
        }


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("delete");
//        String id = req.getParameter("customerId");//null
        String id = req.getParameter("cusID");
        System.out.println(id);
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ezypos", "root", "Prabath550@");
            PreparedStatement pstm = connection.prepareStatement("delete from customer where id=? ");
            pstm.setObject(1, id);
            boolean b = pstm.executeUpdate() > 0;

            if (b) {
                JsonObjectBuilder respo = Json.createObjectBuilder();
                //resp.setStatus(HttpServletResponse.SC_CREATED);
                respo.add("code",200);
                respo.add("message","Deleted..!");
                respo.add("data","");

                writer.print(respo.build());
            }else {
                JsonObjectBuilder respo = Json.createObjectBuilder();
                //resp.setStatus(HttpServletResponse.SC_CREATED);
                respo.add("code",400);
                respo.add("message","");
                respo.add("data","Wrong Id");

                writer.print(respo.build());
            }


        } catch (ClassNotFoundException e) {
            resp.setStatus(200);
            JsonObjectBuilder respo = Json.createObjectBuilder();
           // resp.setStatus(HttpServletResponse.SC_CREATED);
            respo.add("code",500);
            respo.add("message","EROOR..!");
            respo.add("data",e.getLocalizedMessage());

            writer.print(respo.build());

           // resp.sendError(500,e.getMessage());
        } catch (SQLException e) {
            resp.setStatus(200);
            JsonObjectBuilder respo = Json.createObjectBuilder();
           // resp.setStatus(HttpServletResponse.SC_CREATED);
            respo.add("code",500);
            respo.add("message","EROOR..!");
            respo.add("data",e.getLocalizedMessage());

            writer.print(respo.build());
           // resp.sendError(500,e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cid = req.getParameter("customerId");
        String name = req.getParameter("customerName");
        String add = req.getParameter("customerAddress");
        String salary = req.getParameter("customerSalary");

        System.out.println(cid + " " + name + "  " + add + " " + salary);


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade", "root", "Prabath550@");
            PreparedStatement pstm = connection.prepareStatement("update customer set  name=?,address=?,salary=?  where id=? ");

            pstm.setObject(1, name);
            pstm.setObject(2, add);
            pstm.setObject(3, salary);
            pstm.setObject(4, cid);
            boolean b = pstm.executeUpdate() > 0;
            PrintWriter writer = resp.getWriter();
            if (b) {
                writer.write("Customer Updated..!");
            }


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



