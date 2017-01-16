package com.example.sharafievp.filmss.Other;

/**
 * Класс фильма
 */

public class Films {

    private String title;

    private String body_text;

    private String genres;

    private String age_restriction;

    private String poster;

    public String getTitle() {return title;}
    public String getBody_text() {return body_text;}
    public String getGenres() {return genres;}
    public String getAge_restriction() {return age_restriction;}
    public String getPoster() {return poster;}


    public void setTitle(String title) {this.title = title;}
    public void setBody_text(String body_text) {this.body_text = body_text;}
    public void setGenres(String genres) {this.genres = genres;}
    public void setAge_restriction(String age_restriction) {this.age_restriction = age_restriction;}
    public void setPoster(String poster) {this.poster = poster;}
}

