package hk.edu.hkmu.weatherreport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeatherForecasts implements Serializable {
    private List<WeatherForecast> forecasts = new ArrayList<>();

    public void addForecast(WeatherForecast wf) {
        forecasts.add(wf);
    }

    public List<WeatherForecast> getAllForecasts() {
        return forecasts;
    }
}
