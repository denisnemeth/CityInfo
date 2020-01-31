package sample;

import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;

import java.net.URI;
import java.util.List;

public class Controller {

    public ComboBox<String> combo_country;
    public ComboBox combo_city;
    public Label label_country;
    public Label label_city;
    public Button button_1;
    public Label label_cityName;
    public Label label_countryName;
    public Label label_population;
    public Pane pane_details;
    public Label label_code2;
    public Label label_code3;
    public Label label_temp;
    public Label label_humidity;
    public Label label_lon;
    public Label label_lat;
    public CheckBox checkbox_detail;
    public Label label_visibility;
    public Label label_sunrise;
    public Label label_sunset;
    public Button button_map;
    List<String> countries = null;
    private List<City> cities = null;

    public Controller() throws Exception{
        Database db = new Database();
        countries = db.getCountries();
    }
    public void selectCountries(Event event){
        combo_country.getItems().setAll(countries);
    }
    public void showCountries(ActionEvent actionEvent) throws Exception{
        String country = null;
        country = (String) combo_country.getValue();
        if(country != null){
            Database db = new Database();
            cities = db.getCities(country);
            combo_city.getItems().clear();
            for(City c : cities){
                combo_city.getItems().add(c.getName());
            }
            combo_city.setDisable(false);
        }
    }
    public void showCities(ActionEvent actionEvent){
        button_1.setDisable(false);
    }
    public void showData(ActionEvent actionEvent) {
        String cityName = (String) combo_city.getValue();
        City city = null;
        for(City c : cities){
            if(c.getName().equalsIgnoreCase(cityName)){
                city = c;
                break;
            }
        }
        if(city == null){
            return;
        }
        label_cityName.setVisible(true);
        label_countryName.setVisible(true);
        label_population.setVisible(true);
        label_code2.setVisible(true);
        label_code3.setVisible(true);
        label_cityName.setText("  City: " + city.getName());
        label_countryName.setText("  Country: " + city.getCountryName());
        label_population.setText("  Population: " + formatPopulationString(city.getPopulation()));
        label_code2.setText("  Code2: " + city.getCode2());
        label_code3.setText("  Code3: " + city.getCode3());
        WebWeather ww = new WebWeather();
        if(ww.getData(city.getName(), city.getCode2()) != null){
            label_temp.setText("  Temperature: " + ww.getData(city.getName(), city.getCode2()).getTemp() + "°C");
            label_humidity.setText("  Humidity: " + ww.getData(city.getName(), city.getCode2()).getHumidity() + " %");
            label_lon.setText("  Longitude: " + ww.getData(city.getName(), city.getCode2()).getLon());
            label_lat.setText("  Latitude: " + ww.getData(city.getName(), city.getCode2()).getLat());
            label_visibility.setText("  Visibility: " + ww.getData(city.getName(), city.getCode2()).getVisibility() + " m");
            label_sunrise.setText("  Sunrise: " + ww.getData(city.getName(), city.getCode2()).getSunrise() + " AM");
            label_sunset.setText("  Sunset: " + ww.getData(city.getName(), city.getCode2()).getSunset() + " PM");
        } else{
            label_temp.setText("  Temperature: ---°C");
            label_humidity.setText("  Humidity: --- %");
            label_lon.setText("  Longitude: ---");
            label_lat.setText("  Latitude: ---");
            label_visibility.setText("  Visibility: --- m");
            label_sunrise.setText("  Sunrise: --- AM");
            label_sunset.setText("  Sunset: --- PM");
        }
        checkbox_detail.setDisable(false);
        button_map.setDisable(false);
    }
    private String formatPopulationString(int population){
        String data = String.valueOf(population);
        String text = "";
        for(int i = 0; i <= data.length() -1; i++) {
            if(i % 3 == 0)
                text = " " + text;
            text = data.charAt(data.length() -i -1) + text;
        }
        return text.trim();
    }
    public void showDetail(ActionEvent actionEvent) {
        if(checkbox_detail.isSelected()){
            label_temp.setVisible(true);
            label_humidity.setVisible(true);
            label_lon.setVisible(true);
            label_lat.setVisible(true);
            label_visibility.setVisible(true);
            label_sunrise.setVisible(true);
            label_sunset.setVisible(true);
        }else{
            label_temp.setVisible(false);
            label_humidity.setVisible(false);
            label_lon.setVisible(false);
            label_lat.setVisible(false);
            label_visibility.setVisible(false);
            label_sunrise.setVisible(false);
            label_sunset.setVisible(false);
        }
    }
    public void showOnMap(ActionEvent actionEvent){
        WebWeather ww = new WebWeather();
        try {
            java.awt.Desktop.getDesktop().browse(URI.create("https://www.google.com/maps/@" + lat + "," + lon));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
