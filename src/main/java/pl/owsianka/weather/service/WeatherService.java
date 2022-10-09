package pl.owsianka.weather.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import pl.owsianka.weather.interfaces.WeatherApi;
import pl.owsianka.weather.model.WeatherDto;

@Service
public class WeatherService {

    private final WeatherApi weatherApi;

    public WeatherService(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public WeatherDto getWeather(String city) throws JsonProcessingException {
        this.weatherApi.setCity(city);
        WeatherDto weatherDto = this.weatherApi.getWeatherDto();
        return weatherDto;
    }
}
