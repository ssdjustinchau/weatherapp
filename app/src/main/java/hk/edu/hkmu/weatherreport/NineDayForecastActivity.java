package hk.edu.hkmu.weatherreport;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class NineDayForecastActivity extends AppCompatActivity {
    private ListView listView;
    private WeatherForecastAdapter adapter;
    private WeatherForecasts weatherForecasts;
    private Handler handler;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_forecast);

        weatherForecasts = (WeatherForecasts) getIntent().getSerializableExtra("weatherForecasts");

        handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        list = findViewById(R.id.weather_forecast_list);
                        adapter = new WeatherForecastAdapter((Context) NineDayForecastActivity.this, (ArrayList<WeatherForecast>) weatherForecasts.getAllForecasts());
                        list.setAdapter(adapter);
                    }
                });

            }
        }).start();
    }
}