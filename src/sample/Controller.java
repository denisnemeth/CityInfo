package sample;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import java.awt.event.ActionEvent;
import java.util.List;

public class Controller {
    public ComboBox combo_country;
    public ComboBox combo_city;
    public Label label_country;
    public Label label_city;
    public Button button_1;
    List<String> countries;
    public Controller(){
        Database db = new Database();
        try{
            db.getListOfCountries();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*public void enableButton_1(ActionEvent actionEvent){
        while(){
        }
    }*/
}
