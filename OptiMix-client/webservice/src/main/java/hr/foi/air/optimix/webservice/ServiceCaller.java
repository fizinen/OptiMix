package hr.foi.air.optimix.webservice;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Gloria Babić on 31.10.2017..
 */

public class ServiceCaller {


    public static final String HTTP_GET = "GET";
    public static final String HTTP_POST = "POST";
    public static final String HTTP_PUT = "PUT";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";
    public static final String HTTP_DELETE = "DELETE";

    public static ServiceResponse call(URL url, String method, Serializable object, String type, String urlEncoded) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestProperty(CONTENT_TYPE, type);
        connection.setRequestMethod(method);
        connection.connect();


        if(object != null) {


            OutputStream os = connection.getOutputStream();
            os.write(new Gson().toJson(object).getBytes());
            os.close();
        } else if(urlEncoded != null){

            OutputStream os = connection.getOutputStream();
            os.write(urlEncoded.getBytes());
            os.close();

        }

        int code = connection.getResponseCode();
        StringBuilder json = new StringBuilder();
        if(code == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
        }
        String cookie = "";
        if(code == 202){
            cookie = connection.getHeaderField("Set-Cookie");
            cookie = cookie.substring(0, cookie.indexOf(';'));

        }
        connection.disconnect();

        return new ServiceResponse(cookie, json.toString(),code);
    }
}