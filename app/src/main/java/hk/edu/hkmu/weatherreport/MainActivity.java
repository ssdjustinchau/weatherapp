package hk.edu.hkmu.weatherreport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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




