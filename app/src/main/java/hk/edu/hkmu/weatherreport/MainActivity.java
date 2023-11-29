package hk.edu.hkmu.weatherreport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;            // for Intent

//app bar import
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the title for the ActionBar
        getSupportActionBar().setTitle("My Title");

        // Enable the Up button
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Toolbar(cannot use)
        /*
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);*/
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
                // do something
                return true;
            case R.id.action_about:
                // Create an Intent to start AboutActivity
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                return true;
            case R.id.action_settings:
                // do something
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Handler handler = new Handler(Looper.getMainLooper());
    private WeatherApi api = new WeatherApi();
    private WeatherModel weatherdata = new WeatherModel();
    public void updateUI(){
        weatherdata = api.getData();
    }

    private class ComputeTask implements Runnable {  // A task that simulates a long job.
        private long total = 0;
        @Override
        public void run() {
            handler.postDelayed(  // 2
                    new Runnable() {  // 3
                        @Override
                        public void run() {  // 4
                            updateUI();  // 5
                        }
                    },5000
            );
        }
    }
}




