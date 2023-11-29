package hk.edu.hkmu.weatherreport;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherInterfaceFnd {
    @GET("weather.php?dataType=fnd")
    Call<WeatherModelFnd> getData(@Query("lang") String lang);
}
