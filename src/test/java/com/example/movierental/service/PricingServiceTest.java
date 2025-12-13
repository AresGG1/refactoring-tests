package com.example.movierental.service;

import com.example.movierental.model.Movie;
import com.example.movierental.model.MovieType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PricingServiceTest {
    private PricingService pricingService;

    @BeforeEach
    void setUp() {
        pricingService = new PricingService();
    }

    @Test
    void calculateRegularMovieBasePriceFor2Days() {
        Movie movie = new Movie("Test Movie", MovieType.REGULAR);
        double amount = pricingService.calculateRentalAmount(movie, 2);
        assertEquals(2.0, amount);
    }

    @Test
    void calculateRegularMoviePriceFor5Days() {
        Movie movie = new Movie("Test Movie", MovieType.REGULAR);
        double amount = pricingService.calculateRentalAmount(movie, 5);
        assertEquals(6.5, amount);
    }

    @Test
    void calculateNewReleasePriceFor3Days() {
        Movie movie = new Movie("New Movie", MovieType.NEW_RELEASE);
        double amount = pricingService.calculateRentalAmount(movie, 3);
        assertEquals(9.0, amount);
    }

    @Test
    void calculateChildrensPriceFor3Days() {
        Movie movie = new Movie("Kids Movie", MovieType.CHILDRENS);
        double amount = pricingService.calculateRentalAmount(movie, 3);
        assertEquals(1.5, amount);
    }

    @Test
    void calculateChildrensPriceFor5Days() {
        Movie movie = new Movie("Kids Movie", MovieType.CHILDRENS);
        double amount = pricingService.calculateRentalAmount(movie, 5);
        assertEquals(4.5, amount);
    }

    @Test
    void calculateComedyPriceFor2Days() {
        Movie movie = new Movie("Comedy Movie", MovieType.COMEDY);
        double amount = pricingService.calculateRentalAmount(movie, 2);
        assertEquals(2.5, amount);
    }

    @Test
    void calculateDramaPriceFor3Days() {
        Movie movie = new Movie("Drama Movie", MovieType.DRAMA);
        double amount = pricingService.calculateRentalAmount(movie, 3);
        assertEquals(7.0, amount);
    }

    @Test
    void frequentRenterPointsForRegularMovie() {
        Movie movie = new Movie("Test", MovieType.REGULAR);
        int points = pricingService.calculateFrequentRenterPoints(movie, 5);
        assertEquals(1, points);
    }

    @Test
    void frequentRenterPointsForNewReleaseMoreThan1Day() {
        Movie movie = new Movie("New", MovieType.NEW_RELEASE);
        int points = pricingService.calculateFrequentRenterPoints(movie, 3);
        assertEquals(2, points);
    }

    @Test
    void frequentRenterPointsForNewRelease1Day() {
        Movie movie = new Movie("New", MovieType.NEW_RELEASE);
        int points = pricingService.calculateFrequentRenterPoints(movie, 1);
        assertEquals(1, points);
    }
}
