package sample;

import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class Controller {

    public ComboBox combo_country;
    public ComboBox combo_city;
    public Label label_country;
    public Label label_city;
    public Button button_1;
    List<String> countries;

    public Controller() throws SQLException, ClassNotFoundException{
    }
    public String comboValue(){
        return (String) combo_country.getValue();
    }
    public void getCountries(Event event){
        Database db = new Database();
        combo_country.getItems().addAll(db.getCountries());
    }
    public void getCities(Event event){
        Database db = new Database();
        combo_city.getItems().addAll(db.getCities(comboValue()));
    }
    public void enableComboCity(ActionEvent actionEvent){
        combo_city.setDisable(false);
    }
    public void enableButton_1(ActionEvent actionEvent){
        button_1.setDisable(false);
    }
}
