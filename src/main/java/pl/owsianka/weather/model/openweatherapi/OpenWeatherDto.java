package pl.owsianka.weather.model.openweatherapi;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class OpenWeatherDto {
    private OpenWeatherCoordDto coord;
    private ArrayList<OpenWeatherBasicDto> weather;
    private String base;
    private OpenWeatherMainDto main;
    private int visibility;
    private OpenWeatherWindDto wind;
    private OpenWeatherCloudsDto clouds;
    private int dt;
    private OpenWeatherSysDto sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;
}
