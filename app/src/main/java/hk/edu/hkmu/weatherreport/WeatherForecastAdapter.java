package hk.edu.hkmu.weatherreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class WeatherForecastAdapter  extends BaseAdapter {
    private Context context;
    private ArrayList<WeatherForecast> forecasts = null;

    public WeatherForecastAdapter(Context c, ArrayList<WeatherForecast> forecasts) {
        this.context = c;
        this.forecasts = forecasts;
    }

    @Override
    public int getCount() {
        if(forecasts == null)
            return 0;
        return forecasts.size();
    }

    @Override
    public Object getItem(int position) {
        if (forecasts == null || forecasts.isEmpty() || position < 0 || position >= forecasts.size()) {
            return null;
        }
        return forecasts.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.weather_forecast_list_layout, null);
        }

        WeatherForecast forecast = (WeatherForecast) getItem(position);
        TextView forecastDate = convertView.findViewById(R.id.weather_forecast_list_forecastDate);
        ImageView weatherIcon = convertView.findViewById(R.id.weather_forecast_list_weatherIcon);
        TextView temperature = convertView.findViewById(R.id.weather_forecast_list_temperature);
        TextView relativeHumidity = convertView.findViewById(R.id.weather_forecast_list_relativeHumidity);
        ImageView PSRIcon = convertView.findViewById(R.id.weather_forecast_list_PSRIcon);
        TextView PSR = convertView.findViewById(R.id.weather_forecast_list_PSR);
        TextView forecastWeather = convertView.findViewById(R.id.weather_forecast_list_forecastWeather);

        String dateStr = forecast.getForecastDate();
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.BASIC_ISO_DATE);
        DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern("d MMMM");
        String formattedDate = date.format(targetFormatter);
        forecastDate.setText(formattedDate + " (" + forecast.getWeek() + ")" );

        int forecastIcon = forecast.getForecastIcon();
        String forecastIconResourceName = "pic"+forecastIcon;
        int forecastDrawableResource = convertView.getContext().getResources().getIdentifier(forecastIconResourceName, "drawable", convertView.getContext().getPackageName());
        weatherIcon.setImageResource(forecastDrawableResource);


        String celsiusSymbol = "\u2103";
        String temp = forecast.getForecastMinTemp() + "-" + forecast.getForecastMaxTemp() + celsiusSymbol;
        temperature.setText(temp);

        String rh = forecast.getForecastMinRH() + "-" + forecast.getForecastMaxRH() + "%";
        relativeHumidity.setText(rh);

        String psr = forecast.getPSR();
        int psrDrawableResource = 0;
        switch (psr) {
            case "High":
                psrDrawableResource = R.drawable.psr_high;
                break;
            case "Medium High":
                psrDrawableResource = R.drawable.psr_mediumhigh;
                break;
            case "Medium":
                psrDrawableResource = R.drawable.psr_medium;
                break;
            case "Medium Low":
                psrDrawableResource = R.drawable.psr_mediumlow;
                break;
            case "Low":
                psrDrawableResource = R.drawable.psr_low;
                break;
        }
        PSRIcon.setImageResource(psrDrawableResource);

        PSR.setText(psr);

        String fw = forecast.getForecastWeather();
        forecastWeather.setText(fw);

        return convertView;
    }



}
