package dev.thec0dec8ter.tmdb.network;

import dev.thec0dec8ter.tmdb.models.Celebrity;
import dev.thec0dec8ter.tmdb.models.Movie;
import dev.thec0dec8ter.tmdb.models.TvShow;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CelebrityService {

    @GET("person/popular")
    Call<Celebrity> getPopularCelebrities(@Query("api_key") String apiKey, @Query("page") String page);

    @GET("person/{person_id}")
    Call<Celebrity> getCelebDetails(@Path("person_id")String personId, @Query("api_key") String apiKey);

    @GET("person/{person_id}/movie_credits")
    Call<Movie> getMovieCredits(@Path("person_id")String personId, @Query("api_key") String apiKey);

    @GET("person/{person_id}/tv_credits")
    Call<TvShow> getTvCredits(@Path("person_id")String personId, @Query("api_key") String apiKey);

    @GET("person/{person_id}/tagged_images")
    Call<Celebrity> getTaggedImages(@Path("person_id")String personId, @Query("api_key") String apiKey);

}
