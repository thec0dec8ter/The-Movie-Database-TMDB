package dev.thec0dec8ter.tmdb.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Search {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("poster_path")
    @Expose
    private String poster_path;

    @SerializedName("media_type")
    @Expose
    private String media_type;

    @SerializedName("adult")
    @Expose
    private boolean isAdultContent;
    @SerializedName("overview")
    @Expose
    private String overview;


    //movie
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("release_date")
    @Expose
    private String release_date;
    @SerializedName("vote_average")
    @Expose
    private float vote_average;

    //tv
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("original_name")
    @Expose
    private String original_name;
    @SerializedName("first_air_date")
    @Expose
    private String first_air_date;

    //celeb
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("popularity")
    @Expose
    private float popularity;
    @SerializedName("place_of_birth")
    @Expose
    private String place_of_birth;

    @SerializedName("known_for")
    @Expose
    private
    ArrayList<Search> celebShows;

    @SerializedName("page")
    @Expose
    private String page;
    @SerializedName("results")
    @Expose
    private ArrayList<Search> results;
    @SerializedName("total_pages")
    @Expose
    private String total_pages;
    @SerializedName("total_results")
    @Expose
    private String total_results;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public ArrayList<Search> getResults() {
        return results;
    }

    public void setResults(ArrayList<Search> results) {
        this.results = results;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public String getTotal_results() {
        return total_results;
    }

    public void setTotal_results(String total_results) {
        this.total_results = total_results;
    }

    public boolean isAdultContent() {
        return isAdultContent;
    }

    public void setAdultContent(boolean adultContent) {
        isAdultContent = adultContent;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public ArrayList<Search> getCelebShows() {
        return celebShows;
    }

    public void setCelebShows(ArrayList<Search> celebShows) {
        this.celebShows = celebShows;
    }
}
