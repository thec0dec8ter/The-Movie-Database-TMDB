package dev.thec0dec8ter.tmdb.network;

import dev.thec0dec8ter.tmdb.models.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {

    @GET("search/movie")
    Call<Movie> searchForMovies(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") String page);

}
