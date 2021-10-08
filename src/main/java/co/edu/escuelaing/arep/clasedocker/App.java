/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.arep.clasedocker;

import co.edu.escuelaing.arep.clasedocker.Persistence.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.Request;
import spark.Response;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.port;
import static spark.Spark.redirect;
import static spark.Spark.staticFiles;

/**
 *
 * @author Home
 */
public class App {


    public static void main(String... args) {
        Gson gson = new Gson();
        staticFiles.location("/public_html");
        port(getPort());
        get("hello", (req, res) -> "Hola grupo TCON");
        redirect.get("/","/index.html");
        get("/cadenas", "application/json", (req, res) -> (getLimitData(req, res)),gson::toJson);
        post("/cadena",((req, res) -> insertar(req,res)));
        
        

    }

    private static String getLimitData(Request req, Response res) {
        String response = "None";

        HttpStockService stockService = CurrentServiceInstance.getInstance().getService();



        try {
            response = stockService.getStrins();
        } catch (IOException e) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
        }

       System.out.println(response);
       System.out.println(response.replace("\\", ""));

        return response.replace("\\", "");
    }
    
    
    private static String insertar(Request req, Response res){
        System.out.println("reoundRobin!!!");
        System.out.println(req.body());
        JsonParser parser = new JsonParser();
        JsonObject objeto = parser.parse(req.body()).getAsJsonObject();
        HttpStockService stockService = CurrentServiceInstance.getInstance().getService();
        try {
            stockService.postStrin(objeto.toString());
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //service.insertar("");
        return "";
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4568;
    }
}
