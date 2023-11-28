package hk.edu.hkmu.weatherreport;
import retrofit2.Call;
import retrofit2.http.GET;
public interface WeatherInterface {
    @GET("/weather.php?dataType=rhrread&lang=tc")
    Call<WeatherModel> getData();
}
