package dev.thec0dec8ter.tmdb.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CelebrityService {

    @GET("person/popular")
    Call<CelebrityResponse> getPopularCelebrities(@Query("api_key") String apiKey, @Query("page") String page);

}
