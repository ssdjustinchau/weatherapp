package hk.edu.hkmu.weatherreport;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface WeatherInterface {
    @GET("weather.php?dataType=rhrread")
    Call<WeatherModel> getData(@Query("lang") String lang);
}
