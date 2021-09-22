/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.arep.clasedocker.Persistence;


import java.util.Set;



/**
 *
 * @author Home
 */
public interface ServicePersistence {
    
    public void insertar(String string);
    public Set<String> getLimitData(int numeroDatos);
}
