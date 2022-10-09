package pl.owsianka.weather.model.openweatherapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenWeatherWindDto {
    private double speed;
    private int deg;
    private double gust;
}
