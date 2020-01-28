package sample;

import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;

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
                System.out.println(c.getName());
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
        label_cityName.setText("City: "+city.getName());
        label_countryName.setText("Country: "+city.getCountryName());
        label_population.setText("Population: "+formatPopulationString(city.getPopulation()));
    }
    private String formatPopulationString(int population){
        return null;
    }
}
