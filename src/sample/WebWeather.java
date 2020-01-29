package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebWeather {

    public Weather getData(String city, String code2){
        try{
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "," + code2 + "&units=metric&appid=37d1b237e2d02d334784c7f5f8507e7d");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if(connection.getResponseCode() == 200){
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String output = br.readLine();
                JSONObject outputObj = new JSONObject(output);
                JSONObject main = outputObj.getJSONObject("main");
                JSONObject coord = outputObj.getJSONObject("coord");
                JSONObject sys = outputObj.getJSONObject("sys");
                String outputName = outputObj.getString("name");
                String outputCountry = sys.getString("country");
                double outputTemp = main.getDouble("temp");
                int outputHumidity = main.getInt("humidity");
                double outputLon = coord.getDouble("lon");
                double outputLat = coord.getDouble("lat");
                return new Weather(outputName, outputCountry, outputTemp, outputHumidity, outputLon, outputLat);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
