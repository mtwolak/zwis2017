package pwr.edu.pl.zwis2017.db.localization;

import java.util.HashMap;
import java.util.Map;

public class LocalizationWithCityNamer {

    private final String city = "Wrocław";
    private final String cityWithoutPolishChars = getCityWithoutPolishChars(city);
    private static final Map<Character, Character> POLISH_CHAR_TO_ENGLISH;

    static
    {
        POLISH_CHAR_TO_ENGLISH = new HashMap<>();
        POLISH_CHAR_TO_ENGLISH.put('ł', 'l');
        POLISH_CHAR_TO_ENGLISH.put('Ł', 'L');
    }

    public String addCityName(String localization) {
        return hasCityInName(localization) ? localization : appendCityName(localization);
    }

    private String appendCityName(String localization) {
        return localization + ", " + city;
    }

    private boolean hasCityInName(String localization) {
        return isStringInsideAnotherIgnoringCase(city, localization) || isStringInsideAnotherIgnoringCase(cityWithoutPolishChars, localization);
    }

    private boolean isStringInsideAnotherIgnoringCase(String string1, String string2) {
        return string2.toLowerCase().contains(string1.toLowerCase());
    }


    private String getCityWithoutPolishChars(String city) {
        String cityWithoutPolishChars = new String(city);
        for(Map.Entry<Character, Character> polishCharToEnglish : POLISH_CHAR_TO_ENGLISH.entrySet())
        {
            cityWithoutPolishChars = cityWithoutPolishChars.replaceAll(polishCharToEnglish.getKey().toString(), polishCharToEnglish.getValue().toString());
        }
        return cityWithoutPolishChars;
    }
}
