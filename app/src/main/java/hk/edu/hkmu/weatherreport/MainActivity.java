package hk.edu.hkmu.weatherreport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button nineDayForecastBtn;
    private WeatherForecasts weatherForecasts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherForecasts = new WeatherForecasts();

        Thread loadWeatherForecastAPI = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String apiUrl = "https://data.weather.gov.hk/weatherAPI/opendata/weather.php?dataType=fnd&lang=en";
                    URL url = new URL(apiUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String line;
                        StringBuilder response = new StringBuilder();
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        reader.close();
                        String apiResponse = response.toString();

                        //convert api respond to JSON object
                        try {
                            JSONObject json = new JSONObject(apiResponse);
                            JSONArray forecastsArray = json.getJSONArray("weatherForecast");
                            for (int i = 0; i < forecastsArray.length(); i++) {
                                JSONObject forecastJson = forecastsArray.getJSONObject(i);
                                String forecastDate = forecastJson.getString("forecastDate");
                                String week = forecastJson.getString("week");
                                String forecastWind = forecastJson.getString("forecastWind");
                                String forecastWeather = forecastJson.getString("forecastWeather");
                                String forecastMaxTemp = forecastJson.getJSONObject("forecastMaxtemp").getString("value");
                                String forecastMinTemp = forecastJson.getJSONObject("forecastMintemp").getString("value");
                                String forecastMaxRH = forecastJson.getJSONObject("forecastMaxrh").getString("value");
                                String forecastMinRH = forecastJson.getJSONObject("forecastMinrh").getString("value");
                                int forecastIcon = forecastJson.getInt("ForecastIcon");
                                String PSR = forecastJson.getString("PSR");

                                //create WeatherForecast object
                                WeatherForecast forecast = new WeatherForecast(forecastDate, week, forecastWind, forecastWeather,
                                        forecastMaxTemp, forecastMinTemp, forecastMaxRH, forecastMinRH, forecastIcon, PSR);

                                weatherForecasts.addForecast(forecast);
                            }
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Fail to call API: " + responseCode);
                    }
                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        loadWeatherForecastAPI.start();

        nineDayForecastBtn = findViewById(R.id.nineDaysForecast);
        nineDayForecastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NineDayForecastActivity.class);
                intent.putExtra("weatherForecasts", weatherForecasts);
                startActivity(intent);
            }
        });
    }
}