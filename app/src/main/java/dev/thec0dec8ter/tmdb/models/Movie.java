package dev.thec0dec8ter.tmdb.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Movie{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("imdb_id")
    @Expose
    private String imdb_id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("original_title")
    @Expose
    private String original_title;
    @SerializedName("name")
    @Expose
    private String genre_name;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("poster_path")
    @Expose
    private String poster_path;
    @SerializedName("backdrop_path")
    @Expose
    private String backdrop_path;


    @SerializedName("genres")
    @Expose
    private ArrayList<Movie> genres;
    @SerializedName("original_language")
    @Expose
    private String original_language;
    @SerializedName("origin_country")
    @Expose
    private String origin_country;
    @SerializedName("video")
    @Expose
    private boolean video;
    @SerializedName("vote_count")
    @Expose
    private String vote_count;
    @SerializedName("vote_average")
    @Expose
    private float vote_average;
    @SerializedName("popularity")
    @Expose
    private String popularity;
    @SerializedName("logo_path")
    @Expose
    private String logo_path;
    @SerializedName("release_date")
    @Expose
    private String release_date;
    @SerializedName("revenue")
    @Expose
    private String revenue;
    @SerializedName("runtime")
    @Expose
    private int runtime;
    @SerializedName("spoken_languages")
    @Expose
    private ArrayList<Movie> spoken_languages;
    @SerializedName("english_name")
    @Expose
    private String english_name;

    @SerializedName("tagline")
    @Expose
    private String tagline;




    @SerializedName("keywords")
    @Expose
    private ArrayList<Movie> keywords;
    @SerializedName("backdrops")
    @Expose
    private ArrayList<Movie> backdrops;
    @SerializedName("posters")
    @Expose
    private ArrayList<Movie> posters;

    @SerializedName("file_path")
    @Expose
    private String file_path;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("genre_ids")
    @Expose
    private int[] genre_ids;
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

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
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

    public String getGenreName() {
        return genre_name;
    }

    public void setGenreName(String name) {
        this.genre_name = name;
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

    public ArrayList<Movie> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Movie> genres) {
        this.genres = genres;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
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


    public String getLogo_path() {
        return logo_path;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public ArrayList<Movie> getSpoken_languages() {
        return spoken_languages;
    }

    public void setSpoken_languages(ArrayList<Movie> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }


    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public ArrayList<Movie> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<Movie> keywords) {
        this.keywords = keywords;
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

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(int[] genre_ids) {
        this.genre_ids = genre_ids;
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


}
