package pl.owsianka.weather.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CityValidator {

    public static boolean containsSpecialCharacterInTheName(String cityName){
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(cityName);
        return m.find();
    }
}
