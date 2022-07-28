package com.divyakant.ratingdataservice.model;

public class Movie {
    private String movieId;
    private String movie;

    public Movie(String movieId, String movie) {
        this.movieId = movieId;
        this.movie = movie;
    }

    public Movie() {
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }
}
