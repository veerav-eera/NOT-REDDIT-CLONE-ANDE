package com.example.myapplication;

public class Impressions {
    private int impression_id;
    private int user_id;
    private int post_id;
    private int impression_status;

    public Impressions() {}

    public Impressions(int impression_id, int user_id, int post_id, int impression_status) {
        this.impression_id = impression_id;
        this.user_id = user_id;
        this.post_id = post_id;
        this.impression_status = impression_status;
    }

    public int getImpression_id() {
        return impression_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public int getImpression_status() {
        return impression_status;
    }

    public void setImpression_id(int impression_id) {
        this.impression_id = impression_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public void setImpression_status(int impression_status) {
        this.impression_status = impression_status;
    }
}
