package com.example.movierental.model;

public class Rental {
    private Long id;
    private Long customerId;
    private Long movieId;
    private int daysRented;

    public Rental() {
    }

    public Rental(Long customerId, Long movieId, int daysRented) {
        this.customerId = customerId;
        this.movieId = movieId;
        this.daysRented = daysRented;
    }

    public Rental(Long id, Long customerId, Long movieId, int daysRented) {
        this.id = id;
        this.customerId = customerId;
        this.movieId = movieId;
        this.daysRented = daysRented;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }
}
