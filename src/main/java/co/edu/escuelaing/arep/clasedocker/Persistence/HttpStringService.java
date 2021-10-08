/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.arep.clasedocker.Persistence;

/**
 *
 * @author Home
 */
public class HttpStringService extends HttpStockService {
    
    String port = "34000"
            + "";
    String path = "/cadenas";
    String URL = "http://localhost:"+port+path;

    @Override
    public String getURL() {
        return "https://bravenewcoin.p.rapidapi.com/market-cap";
    }
    @Override
    public void setPort(String port){
        this.port = port;
        URL = "http://localhost:"+port+path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
        URL = "http://localhost:"+port+path;
    }



}
