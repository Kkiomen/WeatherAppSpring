package pl.owsianka.weather.model.openweatherapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenWeatherBasicDto {
    private int id;
    private String main;
    private String description;
    private String icon;
}
