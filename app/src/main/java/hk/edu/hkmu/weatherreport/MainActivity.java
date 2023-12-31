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
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;            // for Intent


//app bar import
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import android.media.MediaPlayer;

//time
import android.os.Handler;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



public class MainActivity extends AppCompatActivity {
    private Button nineDayForecastBtn;
    private WeatherForecasts weatherForecasts;

    private MediaPlayer mediaPlayer;

    private TextView DNT;
    private Handler dateTimehandler;
    private Runnable updateTimeRunnable;

    private WeatherDB weatherDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherForecasts = new WeatherForecasts();
        mediaPlayer = MediaPlayer.create(this, R.raw.click_button);

        weatherDB = new WeatherDB(this);

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

                            weatherDB.deleteAllWeather();

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
                                WeatherForecast forecast = new WeatherForecast("0",forecastDate, week, forecastWind, forecastWeather,
                                        forecastMaxTemp, forecastMinTemp, forecastMaxRH, forecastMinRH, forecastIcon, PSR);

                                weatherDB.addWeather(forecast);
                            }

                            weatherForecasts = weatherDB.getWeatherForecasts();

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
                playSound();
                handleVibrationPreference();
                Intent intent = new Intent(MainActivity.this, NineDayForecastActivity.class);
                intent.putExtra("weatherForecasts", weatherForecasts);
                startActivity(intent);
            }
        });

        DNT = findViewById(R.id.DNT);
        dateTimehandler = new Handler();
        updateTimeRunnable = new Runnable() {
            @Override
            public void run() {
                updateTime();
                dateTimehandler.postDelayed(this, 1000);
            }
        };


        // Set the title for the ActionBar
        getSupportActionBar().setTitle("Weather Report");

        //multithreading api call test
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new ApiCall(), 0, 60, TimeUnit.SECONDS);

    }

    @Override
    protected void onResume() {
        super.onResume();
        startUpdatingTime();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopUpdatingTime();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //case android.R.id.home:
                // Navigate back to previous screen
                //onBackPressed();
                //return true;
            case R.id.action_home:
                // Create an Intent to start MainActivity
                playSound();
                handleVibrationPreference();
                Intent homeIntent = new Intent(this, MainActivity.class);
                startActivity(homeIntent);
                return true;
            case R.id.action_about:
                // Create an Intent to start AboutActivity
                playSound();
                handleVibrationPreference();
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                return true;
            case R.id.action_multimedia:
                // Create an Intent to start MultimediaActivity
                playSound();
                handleVibrationPreference();
                Intent multimediaIntent = new Intent(this, MultimediaActivity.class);
                startActivity(multimediaIntent);
                return true;
            case R.id.action_settings:
                // Create an Intent to start SettingsActivity
                playSound();
                handleVibrationPreference();
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_exit:
                // Exit the app
                handleVibrationPreference();
                finishAffinity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Handler handler = new Handler(Looper.getMainLooper());
    private WeatherApi api = new WeatherApi();
    private WeatherModel weatherdata = new WeatherModel();

    public void updateUI() {
        TextView textview = findViewById(R.id.temperature);

//        Log.d("UPDATEUI",weatherdata.getTemperature().getData().get(0).getPlace());


    }

    private class ApiCall implements Runnable {
        private Runnable update = new Runnable() {
            @Override
            public void run() {
                TextView temp = findViewById(R.id.temperature);
                TextView humidity = findViewById(R.id.humidity);
                TextView rainfall = findViewById(R.id.rainfall);
                ImageView bannerimg = findViewById(R.id.bannerimg);

                String celsiusSymbol = "\u2103";
                temp.setText(String.valueOf(weatherdata.getTemperature().getData().get(0).getValue()) + celsiusSymbol);
                humidity.setText(String.valueOf(weatherdata.getHumidity().getData().get(0).getValue()) + "%");
                rainfall.setText(String.valueOf(weatherdata.getRainfall().getData().get(0).getValue()) + "mm");

                int weatherIcon = weatherdata.getIcon().get(0);
                String forecastIconResourceName = "pic"+weatherIcon;
                int forecastDrawableResource = getResources().getIdentifier(forecastIconResourceName, "drawable", getPackageName());
                bannerimg.setImageResource(forecastDrawableResource);

            }
        };

        public void run() {
            weatherdata = api.getData("tc");
            MainActivity.this.runOnUiThread(update);
        }
    }

    private void handleVibrationPreference() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean shouldVibrate = sharedPreferences.getBoolean("pref_key_vibration", true);
        if (shouldVibrate) {
            // Vibrate the device. You'll need the VIBRATE permission for this.
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                Log.d("Vibration", "Vibration started");
            } else {
                vibrator.vibrate(200);
            }
        }
    }

    private void playSound() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean shouldPlaySound = sharedPreferences.getBoolean("pref_key_sound_effects", true);
        if (shouldPlaySound && mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    private void startUpdatingTime() {
        handler.post(updateTimeRunnable);
    }

    private void stopUpdatingTime() {
        handler.removeCallbacks(updateTimeRunnable);
    }

    private void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd | hh:mm a", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        DNT.setText(currentTime);
    }

    @Override
    protected void onDestroy() {
        // Release the MediaPlayer when the activity is destroyed
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}





