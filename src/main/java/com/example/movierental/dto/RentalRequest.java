package com.example.movierental.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RentalRequest {
    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Movie ID is required")
    private Long movieId;

    @Min(value = 1, message = "Days rented must be at least 1")
    private int daysRented;

    public RentalRequest() {
    }

    public RentalRequest(Long customerId, Long movieId, int daysRented) {
        this.customerId = customerId;
        this.movieId = movieId;
        this.daysRented = daysRented;
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
