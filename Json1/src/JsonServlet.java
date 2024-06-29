import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.json.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/json")
public class JsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");//MIMEType
        //singe object send
//        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
//        objectBuilder.add("id","C001");
//        objectBuilder.add("name","Gaaji");
//        objectBuilder.add("salary",100.00);
//        JsonObject build = objectBuilder.build();
//
//        PrintWriter writer = resp.getWriter();
//        writer.print(build);

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("id","C001");
        objectBuilder.add("name","Gaaji");
        objectBuilder.add("salary",100.00);
        arrayBuilder.add(objectBuilder.build());

        JsonObjectBuilder objectBuilder2 = Json.createObjectBuilder();
        objectBuilder.add("id","C001");
        objectBuilder.add("name","Gaaji");
        objectBuilder.add("salary",100.00);

        arrayBuilder.add(objectBuilder.build());
        arrayBuilder.add(objectBuilder2.build());



        PrintWriter writer = resp.getWriter();
        writer.print(arrayBuilder.build());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hiiiiii");


        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        System.out.print(id+" "+name);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonReader reader = Json.createReader(req.getReader());
        JsonArray jsonValues = reader.readArray();
         for (JsonValue js: jsonValues){
             String id = js.asJsonObject().getString("id");
             String name = js.asJsonObject().getString("name");
             String address= js.asJsonObject().getString("address");
             String salary = js.asJsonObject().getString("salary");

             System.out.println(id+""+name+""+address+""+salary);
         }

    }
}
