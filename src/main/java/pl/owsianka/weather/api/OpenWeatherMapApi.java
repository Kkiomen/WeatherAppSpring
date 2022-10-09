package pl.owsianka.weather.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.owsianka.weather.interfaces.WeatherApi;
import pl.owsianka.weather.model.WeatherDto;
import pl.owsianka.weather.model.openweatherapi.OpenWeatherDto;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Component
public class OpenWeatherMapApi implements WeatherApi {

    private final static String URI = "http://api.openweathermap.org/data/2.5/weather";

    @Value("${openweathermap.apikey}")
    private String apiKey;

    private String city;


    private String getUrl() {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(URI)
                .queryParam("q", "{q}")
                .queryParam("APPID", "{APPID}")
                .queryParam("units", "{units}")
                .encode()
                .toUriString();

        return urlTemplate;
    }

    private Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("q", this.city);
        params.put("APPID", this.apiKey);
        params.put("units", "metric");
        return params;
    }

    public String fetchJsonData() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity<>(httpHeaders);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    this.getUrl(),
                    HttpMethod.GET,
                    httpEntity,
                    String.class,
                    this.getParams());
            return response.getBody();
        } catch (HttpClientErrorException ex) {
            if (ex.getRawStatusCode() == 404) {
                throw new NoSuchElementException("No such city found");
            } else {
                throw new IllegalArgumentException("Unexpected error occurred, try again");
            }
        }
    }

    public WeatherDto getWeatherDto() throws JsonProcessingException {

        OpenWeatherDto openWeatherDto = new ObjectMapper().readValue(this.fetchJsonData(), OpenWeatherDto.class);
        return WeatherDto.builder()
                .name(openWeatherDto.getWeather().get(0).getMain())
                .description(openWeatherDto.getWeather().get(0).getDescription())
                .icon(openWeatherDto.getWeather().get(0).getIcon())
                .temperature(openWeatherDto.getMain().getTemp())
                .pressure(openWeatherDto.getMain().getPressure())
                .humidity(openWeatherDto.getMain().getHumidity())
                .windSpeed(openWeatherDto.getWind().getSpeed())
                .cloud(openWeatherDto.getClouds().getAll())
                .city(this.city)
                .build();

    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

}
