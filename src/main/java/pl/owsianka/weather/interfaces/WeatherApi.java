package pl.owsianka.weather.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.owsianka.weather.model.WeatherDto;

public interface WeatherApi {
    String fetchJsonData();
    WeatherDto getWeatherDto() throws JsonProcessingException;

    void setCity(String city);
}
