package hk.edu.hkmu.weatherreport;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class WeatherModelFnd {
    public String generalSituation;
    public ArrayList<WeatherForecast> weatherForecast;
    public Date updateTime;
    public SeaTemp seaTemp;
    public ArrayList<SoilTemp> soilTemp;
    public class Depth{
        public String unit;
        public double value;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }

    public class ForecastMaxrh{
        public int value;
        public String unit;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }

    public class ForecastMaxtemp{
        public int value;
        public String unit;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }

    public class ForecastMinrh{
        public int value;
        public String unit;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }

    public class ForecastMintemp{
        public int value;
        public String unit;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }


    public class SeaTemp{
        public String place;
        public int value;
        public String unit;
        public Date recordTime;

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Date getRecordTime() {
            return recordTime;
        }

        public void setRecordTime(Date recordTime) {
            this.recordTime = recordTime;
        }
    }

    public class SoilTemp{
        public String place;
        public double value;
        public String unit;
        public Date recordTime;
        public Depth depth;

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Date getRecordTime() {
            return recordTime;
        }

        public void setRecordTime(Date recordTime) {
            this.recordTime = recordTime;
        }

        public Depth getDepth() {
            return depth;
        }

        public void setDepth(Depth depth) {
            this.depth = depth;
        }
    }

    public class WeatherForecast{
        public String forecastDate;
        public String week;
        public String forecastWind;
        public String forecastWeather;
        public ForecastMaxtemp forecastMaxtemp;
        public ForecastMintemp forecastMintemp;
        public ForecastMaxrh forecastMaxrh;
        public ForecastMinrh forecastMinrh;
        @SerializedName("ForecastIcon")
        public int forecastIcon;

        public String getForecastDate() {
            return forecastDate;
        }

        public void setForecastDate(String forecastDate) {
            this.forecastDate = forecastDate;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getForecastWind() {
            return forecastWind;
        }

        public void setForecastWind(String forecastWind) {
            this.forecastWind = forecastWind;
        }

        public String getForecastWeather() {
            return forecastWeather;
        }

        public void setForecastWeather(String forecastWeather) {
            this.forecastWeather = forecastWeather;
        }

        public ForecastMaxtemp getForecastMaxtemp() {
            return forecastMaxtemp;
        }

        public void setForecastMaxtemp(ForecastMaxtemp forecastMaxtemp) {
            this.forecastMaxtemp = forecastMaxtemp;
        }

        public ForecastMintemp getForecastMintemp() {
            return forecastMintemp;
        }

        public void setForecastMintemp(ForecastMintemp forecastMintemp) {
            this.forecastMintemp = forecastMintemp;
        }

        public ForecastMaxrh getForecastMaxrh() {
            return forecastMaxrh;
        }

        public void setForecastMaxrh(ForecastMaxrh forecastMaxrh) {
            this.forecastMaxrh = forecastMaxrh;
        }

        public ForecastMinrh getForecastMinrh() {
            return forecastMinrh;
        }

        public void setForecastMinrh(ForecastMinrh forecastMinrh) {
            this.forecastMinrh = forecastMinrh;
        }

        public int getForecastIcon() {
            return forecastIcon;
        }

        public void setForecastIcon(int forecastIcon) {
            this.forecastIcon = forecastIcon;
        }

        public String getpSR() {
            return pSR;
        }

        public void setpSR(String pSR) {
            this.pSR = pSR;
        }

        @SerializedName("PSR")
        public String pSR;
    }

    public String getGeneralSituation() {
        return generalSituation;
    }

    public void setGeneralSituation(String generalSituation) {
        this.generalSituation = generalSituation;
    }

    public ArrayList<WeatherForecast> getWeatherForecast() {
        return weatherForecast;
    }

    public void setWeatherForecast(ArrayList<WeatherForecast> weatherForecast) {
        this.weatherForecast = weatherForecast;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public SeaTemp getSeaTemp() {
        return seaTemp;
    }

    public void setSeaTemp(SeaTemp seaTemp) {
        this.seaTemp = seaTemp;
    }

    public ArrayList<SoilTemp> getSoilTemp() {
        return soilTemp;
    }

    public void setSoilTemp(ArrayList<SoilTemp> soilTemp) {
        this.soilTemp = soilTemp;
    }
}
