package dev.thec0dec8ter.tmdb.network;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/upcoming")
    Call<MovieResponse> getUpcomingMovies(@Query("api_key") String apiKey, @Query("page") String page);

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey,@Query("page") String page);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey,@Query("page") String page);

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("api_key") String apiKey,@Query("language") String language,@Query("page") String page);

    @GET("trending/movie/day")
    Call<MovieResponse> getMoviesTrendingToday(@Query("api_key") String apiKey,@Query("page") String page);

    @GET("trending/movie/week")
    Call<MovieResponse> getMoviesTrendingThisWeek(@Query("api_key") String apiKey,@Query("page") String page);

    @GET("genre/movie/list")
    @SerializedName("genres")
    Call<MovieResponse> getMovieGenres(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Call<MovieResponse> getMovieDetails(@Path("movie_id")String movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/credits")
    Call<CelebrityResponse> getMovieCredits(@Path("movie_id")String movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/images")
    Call<MovieResponse> getMovieImages(@Path("movie_id")String movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/keywords")
    Call<MovieResponse> getMovieKeywords(@Path("movie_id")String movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/similar")
    Call<MovieResponse> getSimilarMovies(@Path("movie_id")String movieId, @Query("api_key") String apiKey,@Query("page") String page);

    @GET("movie/{movie_id}/videos")
    Call<MovieResponse> getMovieVideos(@Path("movie_id")String movieId, @Query("api_key") String apiKey);

    @GET("search/movie")
    Call<MovieResponse> searchForMovies(@Query("api_key") String apiKey, @Query("query") String query,@Query("page") String page);




}
