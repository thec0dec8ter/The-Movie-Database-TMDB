package dev.thec0dec8ter.tmdb.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Comparator;

public class TvResponse {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("original_name")
    @Expose
    private String original_name;
    @SerializedName("overview")
    @Expose
    private String overview;


    @SerializedName("poster_path")
    @Expose
    private String poster_path;
    @SerializedName("backdrop_path")
    @Expose
    private String backdrop_path;
    @SerializedName("file_path")
    @Expose
    private String file_path;

    @SerializedName("first_air_date")
    @Expose
    private String first_air_date;
    @SerializedName("last_air_date")
    @Expose
    private String last_air_date;
    @SerializedName("last_episode_to_air")
    @Expose
    private TvResponse last_episode_to_air;
    @SerializedName("next_episode_to_air")
    @Expose
    private TvResponse next_episode_to_air;
    @SerializedName("air_date")
    @Expose
    private String air_date;
    @SerializedName("episode_number")
    @Expose
    private int episode_number;
    @SerializedName("season_number")
    @Expose
    private int season_number;
    @SerializedName("number_of_episodes")
    @Expose
    private int number_of_episodes;
    @SerializedName("number_of_seasons")
    @Expose
    private int number_of_seasons;
    @SerializedName("still_path")
    @Expose
    private String still_path;
    @SerializedName("genre_ids")
    @Expose
    private int[] genre_ids;
    @SerializedName("episode_runtime")
    @Expose
    private int[] episode_runtime;
    @SerializedName("original_language")
    @Expose
    private String original_language;
    @SerializedName("origin_country")
    @Expose
    private String[] original_country;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("episode_count")
    @Expose
    private int episode_count;
    @SerializedName("vote_count")
    @Expose
    private int vote_count;
    @SerializedName("vote_average")
    @Expose
    private float vote_average;
    @SerializedName("popularity")
    @Expose
    private float popularity;


    @SerializedName("posters")
    @Expose
    private ArrayList<TvResponse> posters;
    @SerializedName("backdrops")
    @Expose
    private ArrayList<TvResponse> backdrops;
    @SerializedName("genres")
    @Expose
    private ArrayList<TvResponse> genres;
    @SerializedName("keywords")
    @Expose
    private ArrayList<TvResponse> keywords;
    @SerializedName("seasons")
    @Expose
    private ArrayList<TvResponse> seasons;

    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("results")
    @Expose
    private ArrayList<TvResponse> results;
    @SerializedName("total_results")
    @Expose
    private int total_results;
    @SerializedName("total_pages")
    @Expose
    private int total_pages;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getLast_air_date() {
        return last_air_date;
    }

    public void setLast_air_date(String last_air_date) {
        this.last_air_date = last_air_date;
    }

    public TvResponse getLast_episode_to_air() {
        return last_episode_to_air;
    }

    public void setLast_episode_to_air(TvResponse last_episode_to_air) {
        this.last_episode_to_air = last_episode_to_air;
    }

    public TvResponse getNext_episode_to_air() {
        return next_episode_to_air;
    }

    public void setNext_episode_to_air(TvResponse next_episode_to_air) {
        this.next_episode_to_air = next_episode_to_air;
    }

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public int getEpisode_number() {
        return episode_number;
    }

    public void setEpisode_number(int episode_number) {
        this.episode_number = episode_number;
    }

    public int getSeason_number() {
        return season_number;
    }

    public void setSeason_number(int season_number) {
        this.season_number = season_number;
    }

    public int getNumber_of_episodes() {
        return number_of_episodes;
    }

    public void setNumber_of_episodes(int number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public int getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(int number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public String getStill_path() {
        return still_path;
    }

    public void setStill_path(String still_path) {
        this.still_path = still_path;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(int[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public int[] getEpisode_runtime() {
        return episode_runtime;
    }

    public void setEpisode_runtime(int[] episode_runtime) {
        this.episode_runtime = episode_runtime;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String[] getOriginal_country() {
        return original_country;
    }

    public void setOriginal_country(String[] original_country) {
        this.original_country = original_country;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public int getEpisode_count() {
        return episode_count;
    }

    public void setEpisode_count(int episode_count) {
        this.episode_count = episode_count;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public ArrayList<TvResponse> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(ArrayList<TvResponse> backdrops) {
        this.backdrops = backdrops;
    }

    public ArrayList<TvResponse> getPosters() {
        return posters;
    }

    public void setPosters(ArrayList<TvResponse> posters) {
        this.posters = posters;
    }

    public ArrayList<TvResponse> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<TvResponse> genres) {
        this.genres = genres;
    }

    public ArrayList<TvResponse> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<TvResponse> seasons) {
        this.seasons = seasons;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<TvResponse> getResults() {
        return results;
    }

    public void setResults(ArrayList<TvResponse> results) {
        this.results = results;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public ArrayList<TvResponse> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<TvResponse> keywords) {
        this.keywords = keywords;
    }


    public static class SortByYear implements Comparator<TvResponse> {

        @Override
        public int compare(TvResponse result1, TvResponse result2) {
            int comparison = result1.getFirst_air_date().split("-")[0].compareTo(result2.getFirst_air_date().split("-")[0]);
            return comparison;
        }
    }

}
