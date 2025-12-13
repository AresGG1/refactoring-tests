package com.example.movierental.dto;

import java.util.List;

public class RentalStatementResponse {
    private String customerName;
    private List<RentalLineItem> rentals;
    private double totalAmount;
    private int frequentRenterPoints;

    public RentalStatementResponse() {
    }

    public RentalStatementResponse(String customerName, List<RentalLineItem> rentals,
                                   double totalAmount, int frequentRenterPoints) {
        this.customerName = customerName;
        this.rentals = rentals;
        this.totalAmount = totalAmount;
        this.frequentRenterPoints = frequentRenterPoints;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<RentalLineItem> getRentals() {
        return rentals;
    }

    public void setRentals(List<RentalLineItem> rentals) {
        this.rentals = rentals;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getFrequentRenterPoints() {
        return frequentRenterPoints;
    }

    public void setFrequentRenterPoints(int frequentRenterPoints) {
        this.frequentRenterPoints = frequentRenterPoints;
    }

    public static class RentalLineItem {
        private String movieTitle;
        private int daysRented;
        private double amount;

        public RentalLineItem() {
        }

        public RentalLineItem(String movieTitle, int daysRented, double amount) {
            this.movieTitle = movieTitle;
            this.daysRented = daysRented;
            this.amount = amount;
        }

        public String getMovieTitle() {
            return movieTitle;
        }

        public void setMovieTitle(String movieTitle) {
            this.movieTitle = movieTitle;
        }

        public int getDaysRented() {
            return daysRented;
        }

        public void setDaysRented(int daysRented) {
            this.daysRented = daysRented;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }
}
