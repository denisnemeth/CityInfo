package sample;

import java.sql.*;
import java.util.List;
import java.util.TimeZone;

public class Database {
    private Connection connection;
    Controller controller = new Controller();
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://itsovy.sk:3306/world_x?serverTimezone=" + TimeZone.getDefault().getID(),
                "student", "kosice2019");
        return connection;
    }
    public List getListOfCountries() throws Exception {
        try{
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT Name FROM country");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                controller.countries.add(rs.getString("Name"));
            }
            controller.combo_country.getItems().addAll(controller.countries);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
