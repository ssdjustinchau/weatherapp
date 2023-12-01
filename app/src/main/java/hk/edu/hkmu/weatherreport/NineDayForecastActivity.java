package hk.edu.hkmu.weatherreport;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;

public class NineDayForecastActivity extends AppCompatActivity {
    private ListView listView;
    private WeatherForecastAdapter adapter;
    private WeatherForecasts weatherForecasts;
    private Handler handler;
    private ListView list;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_forecast);
        mediaPlayer = MediaPlayer.create(this, R.raw.click_button);

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

    @Override
    protected void onDestroy() {
        // Release the MediaPlayer when the activity is destroyed
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
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
            //case android.R.id.home:å
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
                finishAffinity();
                handleVibrationPreference();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}