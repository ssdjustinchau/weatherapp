package hk.edu.hkmu.weatherreport;

import java.util.*;

public class WeatherModel {

    public Rainfall rainfall;
    public String warningMessage;
    public ArrayList<Integer> icon;
    public Date iconUpdateTime;
    public String uvindex;
    public Date updateTime;
    public Temperature temperature;
    public String tcmessage;
    public String mintempFrom00To09;
    public String rainfallFrom00To12;
    public String rainfallLastMonth;
    public String rainfallJanuaryToLastMonth;
    public Humidity humidity;


    public class Datum{
        public String unit;
        public String place;
        public int max;
        public String main;
        public int value;
    }

    public class Humidity{
        public Date recordTime;
        public ArrayList<Datum> data;
    }

    public class Rainfall{
        public ArrayList<Datum> data;
        public Date startTime;
        public Date endTime;
    }
    public class Temperature{
        public ArrayList<Datum> data;
        public Date recordTime;
    }

    public Rainfall getRainfall() {
        return rainfall;
    }

    public void setRainfall(Rainfall rainfall) {
        this.rainfall = rainfall;
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    public ArrayList<Integer> getIcon() {
        return icon;
    }

    public void setIcon(ArrayList<Integer> icon) {
        this.icon = icon;
    }

    public Date getIconUpdateTime() {
        return iconUpdateTime;
    }

    public void setIconUpdateTime(Date iconUpdateTime) {
        this.iconUpdateTime = iconUpdateTime;
    }

    public String getUvindex() {
        return uvindex;
    }

    public void setUvindex(String uvindex) {
        this.uvindex = uvindex;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public String getTcmessage() {
        return tcmessage;
    }

    public void setTcmessage(String tcmessage) {
        this.tcmessage = tcmessage;
    }

    public String getMintempFrom00To09() {
        return mintempFrom00To09;
    }

    public void setMintempFrom00To09(String mintempFrom00To09) {
        this.mintempFrom00To09 = mintempFrom00To09;
    }

    public String getRainfallFrom00To12() {
        return rainfallFrom00To12;
    }

    public void setRainfallFrom00To12(String rainfallFrom00To12) {
        this.rainfallFrom00To12 = rainfallFrom00To12;
    }

    public String getRainfallLastMonth() {
        return rainfallLastMonth;
    }

    public void setRainfallLastMonth(String rainfallLastMonth) {
        this.rainfallLastMonth = rainfallLastMonth;
    }

    public String getRainfallJanuaryToLastMonth() {
        return rainfallJanuaryToLastMonth;
    }

    public void setRainfallJanuaryToLastMonth(String rainfallJanuaryToLastMonth) {
        this.rainfallJanuaryToLastMonth = rainfallJanuaryToLastMonth;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }
}
