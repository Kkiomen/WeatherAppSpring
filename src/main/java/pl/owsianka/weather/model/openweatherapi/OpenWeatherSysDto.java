package pl.owsianka.weather.model.openweatherapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenWeatherSysDto {
    private int type;
    private int id;
    private String country;
    private int sunrise;
    private int sunset;
}
