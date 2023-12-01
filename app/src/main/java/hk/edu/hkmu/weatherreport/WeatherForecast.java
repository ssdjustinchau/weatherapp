package hk.edu.hkmu.weatherreport;

import java.io.Serializable;

public class WeatherForecast implements Serializable {
    private String forecastID;
    private String forecastDate;
    private String week;
    private  String forecastWind;
    private String forecastWeather;
    private String forecastMaxTemp;
    private String forecastMinTemp;
    private String forecastMaxRH;
    private String forecastMinRH;
    private int forecastIcon;
    private String PSR;

    // Constructor
    public WeatherForecast(String forecastID, String forecastDate, String week, String forecastWind,
                            String forecastWeather, String forecastMaxTemp, String forecastMinTemp,
                            String forecastMaxRH, String forecastMinRH, int forecastIcon, String PSR) {

        this.forecastID = forecastID;
        this.forecastDate = forecastDate;
        this.week = week;
        this.forecastWind = forecastWind;
        this.forecastWeather = forecastWeather;
        this.forecastMaxTemp = forecastMaxTemp;
        this.forecastMinTemp = forecastMinTemp;
        this.forecastMaxRH = forecastMaxRH;
        this.forecastMinRH = forecastMinRH;
        this.forecastIcon = forecastIcon;
        this.PSR = PSR;
    }
    public String getForecastID() { return forecastID;  }
    public String getForecastDate() {
        return forecastDate;
    }

    public String getWeek() {
        return week;
    }

    public String getForecastWind() {
        return forecastWind;
    }

    public String getForecastWeather() {
        return forecastWeather;
    }

    public String getForecastMaxTemp() {
        return forecastMaxTemp;
    }

    public String getForecastMinTemp() {
        return forecastMinTemp;
    }

    public String getForecastMaxRH() {
        return forecastMaxRH;
    }

    public String getForecastMinRH() {
        return forecastMinRH;
    }

    public int getForecastIcon() {
        return forecastIcon;
    }

    public String getPSR() {
        return PSR;
    }

}
