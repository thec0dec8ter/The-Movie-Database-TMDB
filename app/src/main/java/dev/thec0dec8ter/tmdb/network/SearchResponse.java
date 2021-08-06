package dev.thec0dec8ter.tmdb.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import dev.thec0dec8ter.tmdb.models.Movie;

public class SearchResponse {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("media_type")
    @Expose
    private String media_type;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gender")
    @Expose
    private int gender;
    @SerializedName("backdrop_path")
    @Expose
    private String backdrop_path;
    @SerializedName("profile_path")
    @Expose
    private String profile_path;
    @SerializedName("release_date")
    @Expose
    private String release_date;
    @SerializedName("first_air_date")
    @Expose
    private String first_air_date;
    @SerializedName("genre_ids")
    @Expose
    private int[] genre_ids;
    @SerializedName("vote_average")
    @Expose
    private float vote_average;



    @SerializedName("page")
    @Expose
    private String page;
    @SerializedName("results")
    @Expose
    private ArrayList<Movie> results;
    @SerializedName("total_pages")
    @Expose
    private String total_pages;
    @SerializedName("total_results")
    @Expose
    private String total_results;

}
