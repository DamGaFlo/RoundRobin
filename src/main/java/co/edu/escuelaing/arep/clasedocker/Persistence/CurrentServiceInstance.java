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
public class CurrentServiceInstance {
    private static CurrentServiceInstance _instance = new CurrentServiceInstance();
    private HttpStockService service;
    private String[] ports = {"34000"};
    //private String[] ports = {"4567"};
    private int actualPort = 0;
    private CurrentServiceInstance(){
        service = new HttpStringService();
    }
    
    public static CurrentServiceInstance getInstance(){
        return _instance;
    }
    
    public HttpStockService getService(){
        service.setPort(ports[actualPort]);
        actualPort = (actualPort+1)%ports.length;
        return service;
    }
    
    public void setService(HttpStockService service){
        this.service = service;
    }
}

