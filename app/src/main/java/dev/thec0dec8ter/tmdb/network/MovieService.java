package dev.thec0dec8ter.tmdb.network;

import com.google.gson.annotations.SerializedName;

import dev.thec0dec8ter.tmdb.models.Celebrity;
import dev.thec0dec8ter.tmdb.models.Genre;
import dev.thec0dec8ter.tmdb.models.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/upcoming")
    Call<Movie> getUpcomingMovies(@Query("api_key") String apiKey, @Query("page") String page);

    @GET("movie/popular")
    Call<Movie> getPopularMovies(@Query("api_key") String apiKey, @Query("page") String page);

    @GET("movie/top_rated")
    Call<Movie> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") String page);

    @GET("movie/now_playing")
    Call<Movie> getNowPlayingMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") String page);

    @GET("trending/movie/day")
    Call<Movie> getMoviesTrendingToday(@Query("api_key") String apiKey, @Query("page") String page);

    @GET("trending/movie/week")
    Call<Movie> getMoviesTrendingThisWeek(@Query("api_key") String apiKey, @Query("page") String page);

    @GET("genre/movie/list")
    @SerializedName("genres")
    Call<Genre> getMovieGenres(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Call<Movie> getMovieDetails(@Path("movie_id")String movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/credits")
    Call<Celebrity> getMovieCredits(@Path("movie_id")String movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/images")
    Call<Movie> getMovieImages(@Path("movie_id")String movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/keywords")
    Call<Movie> getMovieKeywords(@Path("movie_id")String movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/similar")
    Call<Movie> getSimilarMovies(@Path("movie_id")String movieId, @Query("api_key") String apiKey, @Query("page") String page);

    @GET("movie/{movie_id}/videos")
    Call<Movie> getMovieVideos(@Path("movie_id")String movieId, @Query("api_key") String apiKey);




}
