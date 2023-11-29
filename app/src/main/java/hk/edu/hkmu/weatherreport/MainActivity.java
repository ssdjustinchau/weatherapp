package hk.edu.hkmu.weatherreport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

//app bar import
import androidx.appcompat.widget.Toolbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the title for the ActionBar
        getSupportActionBar().setTitle("My Title");

        // Enable the Up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Toolbar(cannot use)
        /*
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);*/

        //multithreading api call test
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new ApiCall(), 0, 60, TimeUnit.SECONDS);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Navigate back to previous screen
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
                Log.d("MULTITHREAD", weatherdata.getIconUpdateTime().toString());
                temp.setText(weatherdata.getTemperature().getData().get(0).getPlace());
            }
        };

        public void run() {
            weatherdata = api.getData();
            MainActivity.this.runOnUiThread(update);
        }
    }
}




