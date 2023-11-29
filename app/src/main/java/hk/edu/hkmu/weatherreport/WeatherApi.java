package hk.edu.hkmu.weatherreport;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Response;
import android.util.Log;

public class WeatherApi {
    private String json;
    private WeatherModel WeatherData;

    public WeatherModel getData(String lang) {
        if (!lang.equals("en")&&!lang.equals("tc")&&!lang.equals("sc"))
            lang = "en";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.weather.gov.hk/weatherAPI/opendata/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherInterface RequestWeather = retrofit.create(WeatherInterface.class);
        try {
            Call<WeatherModel> client = RequestWeather.getData(lang);
            Response res = client.execute();
            Log.d("CALLBACK","url:"+res.raw().request().url());
            WeatherData =  (WeatherModel) res.body();
            Log.d("CALLBACK","onresponse:"+WeatherData.getIconUpdateTime());
        }catch (Exception e){
            e.printStackTrace();
        }
//        client.enqueue(new Callback<WeatherModel>() {
//                           public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
//                                   WeatherData = response.body();
//
//                                   Log.d("UPDATEUI",WeatherData.getTemperature().getData().get(0).getPlace());
//                               return WeatherData;
//                           }
//
//                           public void onFailure(Call<WeatherModel> call, Throwable t) {
//                               Log.d("CALLBACK","error onfailure");
//                               t.printStackTrace();
//
//                           }
//                       });

        return WeatherData;
    }
}
