package example;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private static final double REGULAR_BASE_PRICE = 2.0;
    private static final double REGULAR_EXTRA_DAY_RATE = 1.5;
    private static final int REGULAR_FREE_DAYS = 2;

    private static final double NEW_RELEASE_DAY_RATE = 3.0;

    private static final double CHILDRENS_BASE_PRICE = 1.5;
    private static final double CHILDRENS_EXTRA_DAY_RATE = 1.5;
    private static final int CHILDRENS_FREE_DAYS = 3;

    private final String name;
    private final List<Rental> rentals;

    public Customer(String name, List<Rental> rentals) {
        this.name = name;
        this.rentals = new ArrayList<>(rentals);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        StringBuilder result = new StringBuilder();
        result.append("Rental Record for ").append(getName()).append("\n");

        for (Rental each : rentals) {
            double thisAmount = calculateRentalAmount(each);
            result.append("\t").append(each.getMovie().getTitle())
                  .append("\t").append(thisAmount).append("\n");
        }

        result.append("Amount owed is ").append(calculateTotalAmount()).append("\n");
        result.append("You earned ").append(calculateFrequentRenterPoints()).append(" frequent renter points");

        return result.toString();
    }

    private double calculateRentalAmount(Rental rental) {
        double amount = 0;
        switch (rental.getMovie().getPriceCode()) {
            case REGULAR -> {
                amount += REGULAR_BASE_PRICE;
                if (rental.getDaysRented() > REGULAR_FREE_DAYS) {
                    amount += (rental.getDaysRented() - REGULAR_FREE_DAYS) * REGULAR_EXTRA_DAY_RATE;
                }
            }
            case NEW_RELEASE -> amount += rental.getDaysRented() * NEW_RELEASE_DAY_RATE;
            case CHILDRENS -> {
                amount += CHILDRENS_BASE_PRICE;
                if (rental.getDaysRented() > CHILDRENS_FREE_DAYS) {
                    amount += (rental.getDaysRented() - CHILDRENS_FREE_DAYS) * CHILDRENS_EXTRA_DAY_RATE;
                }
            }
        }
        return amount;
    }

    private double calculateTotalAmount() {
        double total = 0;
        for (Rental rental : rentals) {
            total += calculateRentalAmount(rental);
        }
        return total;
    }

    private int calculateFrequentRenterPoints() {
        int points = 0;
        for (Rental rental : rentals) {
            points++;
            if (rental.getMovie().getPriceCode() == MovieType.NEW_RELEASE && rental.getDaysRented() > 1) {
                points++;
            }
        }
        return points;
    }
}
