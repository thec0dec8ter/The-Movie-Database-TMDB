package dev.thec0dec8ter.tmdb.network;

import dev.thec0dec8ter.tmdb.models.Movie;
import dev.thec0dec8ter.tmdb.models.Search;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {

    @GET("discover/movie")
    Call<Movie> searchForMovies(@Query("api_key") String apiKey,
                                @Query("with_runtime.gte") String runtime,
                                @Query("vote_average.gte") String tmdbRating,
                                @Query("with_genres") String genre,
                                @Query("primary_release_year") String releaseYear,
                                @Query("vote_count.gte") String votes,
                                @Query("language") String language,
                                @Query("page") String page);

    @GET("search/multi")
    Call<Search> multiSearch(@Query("api_key") String apiKey,
                             @Query("query") String query,
                             @Query("page") String page);

    @GET("discover/movie")
    Call<Movie> searchForTvShows(@Query("api_key") String apiKey,
                                @Query("vote_average.gte") String tmdbRating,
                                @Query("with_genres") String genre,
                                @Query("first_air_date_year") String releaseYear,
                                @Query("vote_count.gte") String votes,
                                @Query("language") String language,
                                @Query("page") String page);

}
