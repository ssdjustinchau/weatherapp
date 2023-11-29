package hk.edu.hkmu.weatherreport;

import retrofit2.Call;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Response;
import android.util.Log;
//Nine day Forecast
public class WeatherApiFnd {
    private String json;
    private WeatherModelFnd WeatherData;

    public WeatherModelFnd getData(String lang) {
        if (!lang.equals("en")||!lang.equals("tc")||!lang.equals("sc"))
            lang = "en";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.weather.gov.hk/weatherAPI/opendata/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherInterfaceFnd RequestWeather = retrofit.create(WeatherInterfaceFnd.class);
        Call<WeatherModelFnd> client = RequestWeather.getData(lang);
        try {
            Response res = client.execute();
            WeatherData =  (WeatherModelFnd) res.body();
//            Log.d("CALLBACK2","onresponse:"+WeatherData.getGeneralSituation());

        }catch (Exception e) {e.printStackTrace();}

        return WeatherData;
    }
    public WeatherModelFnd getData() {
        return getData("en");
    }
}
