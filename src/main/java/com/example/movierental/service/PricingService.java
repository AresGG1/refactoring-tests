package com.example.movierental.service;

import com.example.movierental.model.Movie;
import com.example.movierental.model.MovieType;
import org.springframework.stereotype.Service;

@Service
public class PricingService {
    private static final double REGULAR_BASE_PRICE = 2.0;
    private static final double REGULAR_EXTRA_DAY_RATE = 1.5;
    private static final int REGULAR_FREE_DAYS = 2;

    private static final double NEW_RELEASE_DAY_RATE = 3.0;

    private static final double CHILDRENS_BASE_PRICE = 1.5;
    private static final double CHILDRENS_EXTRA_DAY_RATE = 1.5;
    private static final int CHILDRENS_FREE_DAYS = 3;

    private static final double COMEDY_BASE_PRICE = 2.5;
    private static final double COMEDY_EXTRA_DAY_RATE = 1.0;
    private static final int COMEDY_FREE_DAYS = 2;

    private static final double DRAMA_BASE_PRICE = 3.0;
    private static final double DRAMA_EXTRA_DAY_RATE = 2.0;
    private static final int DRAMA_FREE_DAYS = 1;

    public double calculateRentalAmount(Movie movie, int daysRented) {
        double amount = 0;
        switch (movie.getPriceCode()) {
            case REGULAR -> {
                amount += REGULAR_BASE_PRICE;
                if (daysRented > REGULAR_FREE_DAYS) {
                    amount += (daysRented - REGULAR_FREE_DAYS) * REGULAR_EXTRA_DAY_RATE;
                }
            }
            case NEW_RELEASE -> amount += daysRented * NEW_RELEASE_DAY_RATE;
            case CHILDRENS -> {
                amount += CHILDRENS_BASE_PRICE;
                if (daysRented > CHILDRENS_FREE_DAYS) {
                    amount += (daysRented - CHILDRENS_FREE_DAYS) * CHILDRENS_EXTRA_DAY_RATE;
                }
            }
            case COMEDY -> {
                amount += COMEDY_BASE_PRICE;
                if (daysRented > COMEDY_FREE_DAYS) {
                    amount += (daysRented - COMEDY_FREE_DAYS) * COMEDY_EXTRA_DAY_RATE;
                }
            }
            case DRAMA -> {
                amount += DRAMA_BASE_PRICE;
                if (daysRented > DRAMA_FREE_DAYS) {
                    amount += (daysRented - DRAMA_FREE_DAYS) * DRAMA_EXTRA_DAY_RATE;
                }
            }
        }
        return amount;
    }

    public int calculateFrequentRenterPoints(Movie movie, int daysRented) {
        int points = 1;
        if (movie.getPriceCode() == MovieType.NEW_RELEASE && daysRented > 1) {
            points++;
        }
        return points;
    }
}
