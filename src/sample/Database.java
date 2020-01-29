package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class Database {

    private final String GET_COUNTRIES = "SELECT Code, Name FROM country ORDER BY Name";
    private final String GET_CITIES = "SELECT country.Name, city.Name, city.CountryCode, country.Code2, JSON_EXTRACT(Info, '$.Population') AS Info " +
            "FROM city INNER JOIN country ON country.Code = city.CountryCode WHERE country.Name LIKE ? ORDER BY city.Name";

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://itsovy.sk:3306/world_x?serverTimezone=" + TimeZone.getDefault().getID(),
                "student", "kosice2019");
        return connection;
    }
    public List getCountries() throws Exception{
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_COUNTRIES);
        ResultSet rs = statement.executeQuery();
        String country;
        List<String> countryList = new ArrayList<>();
        while(rs.next()){
            country = rs.getString("Name");
            countryList.add(country);
        }
        connection.close();
        return countryList;
    }
    public List getCities(String country) throws Exception{
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_CITIES);
        statement.setString(1, country);
        ResultSet rs = statement.executeQuery();
        List<City> cityList = new ArrayList<>();
        while(rs.next()){
            String countryName = rs.getString("country.Name");
            String name = rs.getString("city.Name");
            String code2 = rs.getString("country.Code2");
            String code3 = rs.getString("city.CountryCode");
            int population = rs.getInt("Info");
            City newCity = new City(countryName, name, population, code2, code3);
            cityList.add(newCity);
        }
        connection.close();
        return cityList;
    }
}
