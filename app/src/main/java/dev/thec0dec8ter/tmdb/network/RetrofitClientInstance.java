package dev.thec0dec8ter.tmdb.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static dev.thec0dec8ter.tmdb.network.NetworkUtils.BASE_URL;

public class RetrofitClientInstance {

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
