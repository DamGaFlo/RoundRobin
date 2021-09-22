/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.arep.clasedocker;

import co.edu.escuelaing.arep.clasedocker.Persistence.ServicePersistence;
import co.edu.escuelaing.arep.clasedocker.Persistence.ServicePersistenceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Set;
import spark.Request;
import spark.Response;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.port;

/**
 *
 * @author Home
 */
public class App {

    private static ServicePersistence service = new ServicePersistenceImpl();



    public static void main(String... args) {
        Gson gson = new Gson();
        port(getPort());
        get("hello", (req, res) -> "Hola grupo TCON");
        get("/", (req, res) -> "Hola grupo TCON");
        get("/cadenas", "application/json", (req, res) -> (getLimitData(req, res)),gson::toJson);
        post("/cadena",((req, res) -> insertar(req,res)));
        
        

    }

    private static Set<String> getLimitData(Request req, Response res) {

        return service.getLimitData(10);
    }
    
    private static String insertar(Request req, Response res){
        System.out.println("possssstmaaaaaaaaaaaaaaaaaan");
        System.out.println(req.body()+"   <--este es el body");
        //String result = new String(req.body(), "UTF-8");
        //System.out.println(result);
        JsonParser parser = new JsonParser();
        JsonObject objeto = parser.parse(req.body()).getAsJsonObject();
        service.insertar(objeto.get("string").getAsString());
        
        
        return "";
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
