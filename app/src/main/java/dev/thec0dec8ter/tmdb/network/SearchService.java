package dev.thec0dec8ter.tmdb.network;

import java.util.Map;

import dev.thec0dec8ter.tmdb.models.Search;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface SearchService {

    @GET("discover/movie")
    Call<Search> discoverMovies(@Query("api_key") String apiKey, @Query("page") String page, @QueryMap Map<String, String> params);

    @GET("discover/tv")
    Call<Search> discoverTvShows(@Query("api_key") String apiKey, @Query("page") String page, @QueryMap Map<String, String> params);

    @GET("search/multi")
    Call<Search> multiSearch(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") String page);

}
