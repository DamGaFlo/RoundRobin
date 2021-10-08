/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.arep.clasedocker.Persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Home
 */
public abstract class HttpStockService {

    private static final String USER_AGENT = "Mozilla/5.0";

    public String getStrins() throws IOException {
        String responseStr = "None";
        setPath("/cadenas");
        URL obj = new URL(getURL());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.addRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("GET Response code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            responseStr = response.toString();
            System.out.println(responseStr);

        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return responseStr;

    }

    public String postStrin(String jsonInputString) throws IOException {
        String responseStr = "None";
        setPath("/cadena");
        URL obj = new URL(getURL());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        con.addRequestProperty("User-Agent", USER_AGENT);
        

        //int responseCode = con.getResponseCode();
        //System.out.println("POST Response code :: " + responseCode);

        try ( OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try ( BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }

        

        System.out.println(
                "GET DONE");
        return responseStr;

    }

    public abstract String getURL();

    public abstract void setPort(String port);

    public abstract void setPath(String path);

}
