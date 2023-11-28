package hk.edu.hkmu.weatherreport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.MenuItem;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Toolbar(cannot use)
        /*
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);*/
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
}

