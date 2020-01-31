package sample;

public class Weather {

    private String name;
    private String country;
    private double temp;
    private int humidity;
    private double lon;
    private double lat;
    private int visibility;
    private String sunrise;
    private String sunset;

    public Weather(String name, String country, double temp, int humidity, double lon, double lat, int visibility, String sunrise, String sunset) {
        this.name = name;
        this.country = country;
        this.temp = temp;
        this.humidity = humidity;
        this.lon = lon;
        this.lat = lat;
        this.visibility = visibility;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }
    public String getName() {
        return name;
    }
    public String getCountry() {
        return country;
    }
    public double getTemp() {
        return temp;
    }
    public int getHumidity() {
        return humidity;
    }
    public double getLon() {
        return lon;
    }
    public double getLat() {
        return lat;
    }
    public int getVisibility() {
        return visibility;
    }
    public String getSunrise() {
        return sunrise;
    }
    public String getSunset() {
        return sunset;
    }
}
