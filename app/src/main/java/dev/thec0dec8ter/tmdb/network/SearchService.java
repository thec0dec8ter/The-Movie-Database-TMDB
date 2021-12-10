package dev.thec0dec8ter.tmdb.network;

import dev.thec0dec8ter.tmdb.models.Search;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface SearchService {

    @GET()
    Call<Search> discoverShows(@Url String url);

    @GET("search/multi")
    Call<Search> multiSearch(@Query("api_key") String apiKey,
                             @Query("query") String query,
                             @Query("page") String page);

}
