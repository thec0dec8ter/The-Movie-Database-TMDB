package dev.thec0dec8ter.tmdb.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CelebrityService {

    @GET("person/popular")
    Call<CelebrityResponse> getPopularCelebrities(@Query("api_key") String apiKey, @Query("page") String page);

    @GET("person/{person_id}")
    Call<CelebrityResponse> getCelebDetails(@Path("person_id")String personId, @Query("api_key") String apiKey);

    @GET("person/{person_id}/movie_credits")
    Call<CelebrityResponse> getMovieCredits(@Path("person_id")String personId, @Query("api_key") String apiKey);

    @GET("person/{person_id}/tv_credits")
    Call<CelebrityResponse> getTvCredits(@Path("person_id")String personId, @Query("api_key") String apiKey);

    @GET("person/{person_id}/tagged_images")
    Call<CelebrityResponse> getTaggedImages(@Path("person_id")String personId, @Query("api_key") String apiKey);

}
