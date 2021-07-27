package dev.thec0dec8ter.tmdb.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CelebrityResponse {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("original_name")
    @Expose
    private String original_name;
    @SerializedName("profile_path")
    @Expose
    private String profile_path;
    @SerializedName("biography")
    @Expose
    private String biography;
    @SerializedName("character")
    @Expose
    private String character_name;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("job")
    @Expose
    private String job;
    @SerializedName("gender")
    @Expose
    private int gender;

    @SerializedName("adult")
    @Expose
    private boolean adult_content;
    @SerializedName("popularity")
    @Expose
    private float popularity;
    @SerializedName("known_for_department")
    @Expose
    private String known_for_department;
    @SerializedName("known_for")
    @Expose
    private ArrayList<CelebrityResponse> known_for;
    @SerializedName("cast")
    @Expose
    private ArrayList<CelebrityResponse> cast;
    @SerializedName("crew")
    @Expose
    private ArrayList<CelebrityResponse> crew;

    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("results")
    @Expose
    private ArrayList<CelebrityResponse> results;
    @SerializedName("total_pages")
    @Expose
    private int total_pages;
    @SerializedName("total_results")
    @Expose
    private int total_results;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getCharacter_name() {
        return character_name;
    }

    public void setCharacter_name(String character_name) {
        this.character_name = character_name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean isAdult_content() {
        return adult_content;
    }

    public void setAdult_content(boolean adult_content) {
        this.adult_content = adult_content;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getKnown_for_department() {
        return known_for_department;
    }

    public void setKnown_for_department(String known_for_department) {
        this.known_for_department = known_for_department;
    }

    public ArrayList<CelebrityResponse> getCast() {
        return cast;
    }

    public void setCast(ArrayList<CelebrityResponse> cast) {
        this.cast = cast;
    }

    public ArrayList<CelebrityResponse> getCrew() {
        return crew;
    }

    public void setCrew(ArrayList<CelebrityResponse> crew) {
        this.crew = crew;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<CelebrityResponse> getResults() {
        return results;
    }

    public void setResults(ArrayList<CelebrityResponse> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public ArrayList<CelebrityResponse> getKnown_for() {
        return known_for;
    }

    public void setKnown_for(ArrayList<CelebrityResponse> known_for) {
        this.known_for = known_for;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
