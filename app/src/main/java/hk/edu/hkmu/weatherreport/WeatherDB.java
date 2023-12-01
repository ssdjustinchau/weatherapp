package hk.edu.hkmu.weatherreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WeatherDB extends SQLiteOpenHelper {
    private	static final int DATABASE_VERSION =	1;
    private static final String DATABASE_NAME = "weather.db";
    private static final String TABLE_NAME = "weather";
    private static final  String[] COLUMNS = {"forecastId", "forecastDate", "week", "forecastWind", "forecastWeather", "forecastMaxTemp", "forecastMinTemp", "forecastMaxRH", "forecastMinRH", "forecastIcon", "PSR"};

    public WeatherDB(Context context) {super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String TABLE_CREATION = "create table if not exists " + TABLE_NAME + "( "
                + COLUMNS[0] + " integer primary key autoincrement, "
                + COLUMNS[1] + " String not null, "
                + COLUMNS[2] + " String not null, "
                + COLUMNS[3] + " String not null, "
                + COLUMNS[4] + " String not null, "
                + COLUMNS[5] + " String not null, "
                + COLUMNS[6] + " String not null, "
                + COLUMNS[7] + " String not null, "
                + COLUMNS[8] + " String not null, "
                + COLUMNS[9] + " integer not null, "
                + COLUMNS[10] + " String not null" + ")";
        db.execSQL(TABLE_CREATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public WeatherForecasts getWeatherForecasts() {
        WeatherForecasts weatherForecasts = new WeatherForecasts();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String forecastId = cursor.getString(0);
            String forecastDate = cursor.getString(1);
            String week = cursor.getString(2);
            String forecastWind = cursor.getString(3);
            String forecastWeather = cursor.getString(4);
            String forecastMaxTemp = cursor.getString(5);
            String forecastMinTemp = cursor.getString(6);
            String forecastMaxRH = cursor.getString(7);
            String forecastMinRH = cursor.getString(8);
            int forecastIcon = cursor.getInt(9);
            String PSR = cursor.getString(10);
            weatherForecasts.addForecast(forecastId, forecastDate, week, forecastWind, forecastWeather, forecastMaxTemp, forecastMinTemp, forecastMaxRH, forecastMinRH, forecastIcon, PSR);
        }
        cursor.close();
        return weatherForecasts;
    }

     public void addWeather(WeatherForecast weatherForecast) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMNS[1], weatherForecast.getForecastDate());
         cv.put(COLUMNS[2], weatherForecast.getWeek());
         cv.put(COLUMNS[3], weatherForecast.getForecastWind());
         cv.put(COLUMNS[4], weatherForecast.getForecastWeather());
         cv.put(COLUMNS[5], weatherForecast.getForecastMaxTemp());
         cv.put(COLUMNS[6], weatherForecast.getForecastMinTemp());
         cv.put(COLUMNS[7], weatherForecast.getForecastMaxRH());
         cv.put(COLUMNS[8], weatherForecast.getForecastMinRH());
         cv.put(COLUMNS[9], weatherForecast.getForecastIcon());
         cv.put(COLUMNS[10], weatherForecast.getPSR());
         db.insert(TABLE_NAME, null, cv);
     }

     public void deleteWeather(int forecastID){
        SQLiteDatabase db = this.getWritableDatabase();
         db.delete(TABLE_NAME, COLUMNS[0]	+ "	= ?", new String[] { String.valueOf(forecastID)});
     }

}
