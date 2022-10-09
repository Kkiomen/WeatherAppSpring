package pl.owsianka.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.owsianka.weather.model.WeatherDto;
import pl.owsianka.weather.service.WeatherService;
import pl.owsianka.weather.validator.CityValidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {

    private final WeatherService weatherService;

    public HomeController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    String index(@ModelAttribute("error") String errorMessage, Model model) {
        model.addAttribute("errorMessage", errorMessage);
        return "index";
    }

    @GetMapping("/weather/{city}")
    String weather(@PathVariable("city") String city, RedirectAttributes redirectAttributes, Model model) {
        try {

            if(CityValidator.containsSpecialCharacterInTheName(city))
                throw new IllegalArgumentException("The city name must not contain special characters");


            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            LocalDateTime now = LocalDateTime.now();


            WeatherDto weather = weatherService.getWeather(city);
            weather.setIcon("http://openweathermap.org/img/wn/" + weather.getIcon() + "@2x.png");

            model.addAttribute("weather", weather);
            model.addAttribute("currentTime", dtf.format(now));

            return "weather";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/";
        }


    }

    @PostMapping("go-to-weather")
    public String findProduct(@RequestParam("city") String city) {
        return "redirect:/weather/" + city;
    }


}
