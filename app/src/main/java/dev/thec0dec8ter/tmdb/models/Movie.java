package dev.thec0dec8ter.tmdb.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Movie{

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("poster_path")
    @Expose
    private String poster_path;

    @SerializedName("backdrop_path")
    @Expose
    private String backdrop_path;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("original_title")
    @Expose
    private String original_title;

    @SerializedName("release_date")
    @Expose
    private String release_date;

    @SerializedName("runtime")
    @Expose
    private int runtime;

    @SerializedName("tagline")
    @Expose
    private String tagline;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("popularity")
    @Expose
    private String popularity;

    @SerializedName("adult")
    @Expose
    private boolean isAdultContent;

    @SerializedName("vote_average")
    @Expose
    private float vote_average;

    @SerializedName("file_path")
    @Expose
    private String file_path;

    @SerializedName("backdrops")
    @Expose
    private ArrayList<Movie> backdrops;

    @SerializedName("posters")
    @Expose
    private ArrayList<Movie> posters;

    @SerializedName("genre_ids")
    @Expose
    private ArrayList<Integer> genre_ids;

    @SerializedName("genres")
    @Expose
    private ArrayList<Genre> genres;

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

    public Movie(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
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

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(ArrayList<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public ArrayList<Movie> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(ArrayList<Movie> backdrops) {
        this.backdrops = backdrops;
    }

    public ArrayList<Movie> getPosters() {
        return posters;
    }

    public void setPosters(ArrayList<Movie> posters) {
        this.posters = posters;
    }
}
