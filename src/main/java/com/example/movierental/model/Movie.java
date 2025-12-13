package com.example.movierental.model;

public class Movie {
    private Long id;
    private String title;
    private MovieType priceCode;

    public Movie() {
    }

    public Movie(String title, MovieType priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public Movie(Long id, String title, MovieType priceCode) {
        this.id = id;
        this.title = title;
        this.priceCode = priceCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MovieType getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(MovieType priceCode) {
        this.priceCode = priceCode;
    }
}
