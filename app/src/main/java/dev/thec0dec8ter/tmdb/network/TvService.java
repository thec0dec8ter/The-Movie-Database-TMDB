package dev.thec0dec8ter.tmdb.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TvService {

    @GET("tv/popular")
    Call<TvResponse> getPopularTvShows(@Query("api_key") String apiKey, @Query("page") String page);

    @GET("tv/top_rated")
    Call<TvResponse> getTopRatedTvShows(@Query("api_key") String apiKey,@Query("page") String page);

    @GET("tv/airing_today")
    Call<TvResponse> getTvShowAiringToday(@Query("api_key") String apiKey,@Query("page") String page);

    @GET("tv/on_the_air")
    Call<TvResponse> getTvShowAiringThisWeek(@Query("api_key") String apiKey,@Query("page") String page);

    @GET("trending/tv/day")
    Call<TvResponse> getTvShowsTrendingToday(@Query("api_key") String apiKey,@Query("page") String page);

    @GET("trending/tv/week")
    Call<TvResponse> getTvShowsTrendingThisWeek(@Query("api_key") String apiKey,@Query("page") String page);

    @GET("genre/tv/list")
    Call<TvResponse> getTvShowGenres(@Query("api_key") String apiKey);

    @GET("tv/{tv_id}")
    Call<TvResponse> getTvShowDetails(@Path("tv_id")String tvId, @Query("api_key") String apiKey);

    @GET("tv/{tv_id}/credits")
    Call<CelebrityResponse> getTvShowCredits(@Path("tv_id")String tvId, @Query("api_key") String apiKey);

    @GET("tv/{tv_id}/images")
    Call<TvResponse> getTvShowImages(@Path("tv_id")String tvId, @Query("api_key") String apiKey);

    @GET("tv/{tv_id}/keywords")
    Call<TvResponse> getTvShowKeywords(@Path("tv_id")String tvId, @Query("api_key") String apiKey);

    @GET("tv/{tv_id}/similar")
    Call<TvResponse> getSimilarTvShows(@Path("tv_id")String tvId, @Query("api_key") String apiKey,@Query("page") String page);

    @GET("tv/{tv_id}/videos")
    Call<TvResponse> getTvShowVideos(@Path("tv_id")String tvId);

}
