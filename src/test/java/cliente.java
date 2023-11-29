
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class cliente {
    

    public static void main(String[] args) {
        
        String api = "http://localhost/quinto-api/api.php";
    
        HttpClient cliente  = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(api))
                .GET()
                .build();
        
        try {
            
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

            Type listType = new TypeToken<ArrayList<Object>>(){}.getType();
            ArrayList<Object> lista = new Gson().fromJson(respuesta.body(), listType);
            
            StringBuilder tablaBody = new StringBuilder();
            
            
            lista.forEach((item) -> {
            
                String fila = "<tr>" +
                        "<td>" + ((LinkedTreeMap) item).get("CED_EST").toString() + "</td>" +
                        "<td>" + ((LinkedTreeMap) item).get("NOM_EST").toString() + "</td>" +
                        "<td>" + ((LinkedTreeMap) item).get("APE_EST").toString() + "</td>" +
                        "<td>" + ((LinkedTreeMap) item).get("DIR_EST").toString() + "</td>" +
                        "<td>" + ((LinkedTreeMap) item).get("TEL_EST").toString() + "</td>" +
                        "<td>" +
                        "<a href=\"update_person.jsp?CED_EST=" + ((LinkedTreeMap) item).get("CED_EST").toString() + "\">" +
                        "<button type=\"button\" class=\"btn btn-primary\">ACTUALIZAR</button></a>\t" +
                        "<a href=\"delete_person.jsp?CED_EST=" + ((LinkedTreeMap) item).get("CED_EST").toString() + "\">" +
                        "<button type=\"button\" class=\"btn btn-danger\">ELIMINAR</button></a>" +
                        "</td>" +
                        "</tr>";

                        tablaBody.append(fila);
                
                
            });
            
            System.out.println(tablaBody.toString());


            
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
