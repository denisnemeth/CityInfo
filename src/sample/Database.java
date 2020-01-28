package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class Database {

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://itsovy.sk:3306/world_x?serverTimezone=" + TimeZone.getDefault().getID(),
                "student", "kosice2019");
        return connection;
    }
    public List getCountries(){
        try{
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT Name FROM country");
            ResultSet rs = statement.executeQuery();
            String country;
            List<String> countryList = new ArrayList<>();
            while(rs.next()){
                country = rs.getString("Name");
                countryList.add(country);
            }
            return countryList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List getCities(String countryName){
        try{
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT city.Name FROM city INNER JOIN country ON " +
                    "country.Code = city.CountryCode WHERE country.Name LIKE ?");
            statement.setString(1, countryName);
            ResultSet rs = statement.executeQuery();
            String city;
            List<String> cityList = new ArrayList<>();
            while(rs.next()){
                city = rs.getString("Name");
                cityList.add(city);
            }
            return cityList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
