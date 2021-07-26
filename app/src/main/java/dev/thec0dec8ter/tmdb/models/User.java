package dev.thec0dec8ter.tmdb.models;

import java.util.HashMap;

public class User {
    private String id;
    private String name;
    private String email;
    private HashMap<String, String> watchlist;
    private HashMap<String, String> recent;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String, String> getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(HashMap<String, String> watchlist) {
        this.watchlist = watchlist;
    }

    public HashMap<String, String> getRecent() {
        return recent;
    }

    public void setRecent(HashMap<String, String> recent) {
        this.recent = recent;
    }
}
