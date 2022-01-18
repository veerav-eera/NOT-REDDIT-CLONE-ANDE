package com.example.myapplication;

public class Post {
    private int post_id;
    private String post_title;
    private String post_content;
    private int post_likes;
    private int post_dislikes;
    private int post_creator;

    public Post() {
    }

    public Post(int post_id, String post_title, String post_content, int post_likes, int post_dislikes, int post_creator) {
        this.post_id = post_id;
        this.post_title = post_title;
        this.post_content = post_content;
        this.post_likes = post_likes;
        this.post_dislikes = post_dislikes;
        this.post_creator = post_creator;
    }

    public int getPost_id() {
        return post_id;
    }

    public String getPost_title() {
        return post_title;
    }

    public String getPost_content() {
        return post_content;
    }

    public int getPost_likes() {
        return post_likes;
    }

    public int getPost_dislikes() {
        return post_dislikes;
    }

    public int getPost_creator() {
        return post_creator;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public void setPost_likes(int post_likes) {
        this.post_likes = post_likes;
    }

    public void setPost_dislikes(int post_dislikes) {
        this.post_dislikes = post_dislikes;
    }

    public void setPost_creator(int post_creator) {
        this.post_creator = post_creator;
    }

    @Override
    public String toString() {
        return "Post{" +
                "post_id=" + post_id +
                ", post_title='" + post_title + '\'' +
                ", post_content='" + post_content + '\'' +
                ", post_likes=" + post_likes +
                ", post_dislikes=" + post_dislikes +
                ", post_creator=" + post_creator +
                '}';
    }
}
