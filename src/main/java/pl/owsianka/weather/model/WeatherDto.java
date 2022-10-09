package pl.owsianka.weather.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WeatherDto {
    private String city;
    private String name;
    private String description;
    private String icon;
    private int temperature;
    private int pressure;
    private int humidity;
    private double windSpeed;
    private int cloud;
}
