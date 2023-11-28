package hk.edu.hkmu.weatherreport;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Response;

public class WeatherApi {
    private String json;
    private WeatherModel WeatherData;

    public WeatherModel getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.weather.gov.hk/weatherAPI/opendata")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherInterface RequestWeather = retrofit.create(WeatherInterface.class);
        Call<WeatherModel> client = RequestWeather.getData();
        client.enqueue(new Callback<WeatherModel>() {
                           public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                               if (response.code() == 200) {
                                   WeatherData = response.body();
                               }
                           }

                           public void onFailure(Call<WeatherModel> call, Throwable t) {
                           }
                       }
        );
        return WeatherData;
    }
}
