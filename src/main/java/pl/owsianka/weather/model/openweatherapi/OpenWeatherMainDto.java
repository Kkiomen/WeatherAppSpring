package pl.owsianka.weather.model.openweatherapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenWeatherMainDto {
    private int temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    private int pressure;
    private int humidity;
}
