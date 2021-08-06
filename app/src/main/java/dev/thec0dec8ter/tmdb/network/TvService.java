package dev.thec0dec8ter.tmdb.network;

import dev.thec0dec8ter.tmdb.models.Celebrity;
import dev.thec0dec8ter.tmdb.models.TvShow;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TvService {

    @GET("tv/popular")
    Call<TvShow> getPopularTvShows(@Query("api_key") String apiKey, @Query("page") String page);

    @GET("tv/top_rated")
    Call<TvShow> getTopRatedTvShows(@Query("api_key") String apiKey, @Query("page") String page);

    @GET("tv/airing_today")
    Call<TvShow> getTvShowAiringToday(@Query("api_key") String apiKey, @Query("page") String page);

    @GET("tv/on_the_air")
    Call<TvShow> getTvShowAiringThisWeek(@Query("api_key") String apiKey, @Query("page") String page);

    @GET("trending/tv/day")
    Call<TvShow> getTvShowsTrendingToday(@Query("api_key") String apiKey, @Query("page") String page);

    @GET("trending/tv/week")
    Call<TvShow> getTvShowsTrendingThisWeek(@Query("api_key") String apiKey, @Query("page") String page);

    @GET("genre/tv/list")
    Call<TvShow> getTvShowGenres(@Query("api_key") String apiKey);

    @GET("tv/{tv_id}")
    Call<TvShow> getTvShowDetails(@Path("tv_id")String tvId, @Query("api_key") String apiKey);

    @GET("tv/{tv_id}/credits")
    Call<Celebrity> getTvShowCredits(@Path("tv_id")String tvId, @Query("api_key") String apiKey);

    @GET("tv/{tv_id}/images")
    Call<TvShow> getTvShowImages(@Path("tv_id")String tvId, @Query("api_key") String apiKey);

    @GET("tv/{tv_id}/keywords")
    Call<TvShow> getTvShowKeywords(@Path("tv_id")String tvId, @Query("api_key") String apiKey);

    @GET("tv/{tv_id}/similar")
    Call<TvShow> getSimilarTvShows(@Path("tv_id")String tvId, @Query("api_key") String apiKey, @Query("page") String page);

    @GET("tv/{tv_id}/videos")
    Call<TvShow> getTvShowVideos(@Path("tv_id")String tvId);

}
