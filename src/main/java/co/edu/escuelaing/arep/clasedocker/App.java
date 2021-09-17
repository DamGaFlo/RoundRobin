/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.arep.clasedocker;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 *
 * @author Home
 */
public class App {
    
    public static void main(String... args){
          port(getPort());
          get("hello", (req,res) -> "Hola grupo TCON");
          get("/", (req,res) -> "Hola grupo TCON");
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
