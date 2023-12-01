package hk.edu.hkmu.weatherreport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeatherForecasts implements Serializable {
    private List<WeatherForecast> forecasts = new ArrayList<>();

    public void addForecast(String forecastId, String forecastDate, String week, String forecastWind, String forecastWeather, String forecastMaxTemp, String forecastMinTemp, String forecastMaxRH, String forecastMinRH, int forecastIcon, String PSR) {
        WeatherForecast wf = new WeatherForecast(forecastId, forecastDate, week, forecastWind, forecastWeather, forecastMaxTemp, forecastMinTemp, forecastMaxRH, forecastMinRH, forecastIcon, PSR);
        forecasts.add(wf);
    }

    public List<WeatherForecast> getAllForecasts() {
        return forecasts;
    }

    public void clear() {
        forecasts.clear();
    }
}
