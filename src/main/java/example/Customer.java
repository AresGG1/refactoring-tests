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

    private static final double COMEDY_BASE_PRICE = 2.5;
    private static final double COMEDY_EXTRA_DAY_RATE = 1.0;
    private static final int COMEDY_FREE_DAYS = 2;

    private static final double DRAMA_BASE_PRICE = 3.0;
    private static final double DRAMA_EXTRA_DAY_RATE = 2.0;
    private static final int DRAMA_FREE_DAYS = 1;

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
        result.append("## Rental Record for ").append(getName()).append("\n\n");
        result.append("| Movie | Amount |\n");
        result.append("|-------|-------:|\n");

        for (Rental each : rentals) {
            double thisAmount = calculateRentalAmount(each);
            result.append("| ").append(each.getMovie().getTitle())
                  .append(" | ").append(thisAmount).append(" |\n");
        }

        result.append("\n**Amount owed:** ").append(calculateTotalAmount()).append("\n\n");
        result.append("**Frequent renter points:** ").append(calculateFrequentRenterPoints());

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
            case COMEDY -> {
                amount += COMEDY_BASE_PRICE;
                if (rental.getDaysRented() > COMEDY_FREE_DAYS) {
                    amount += (rental.getDaysRented() - COMEDY_FREE_DAYS) * COMEDY_EXTRA_DAY_RATE;
                }
            }
            case DRAMA -> {
                amount += DRAMA_BASE_PRICE;
                if (rental.getDaysRented() > DRAMA_FREE_DAYS) {
                    amount += (rental.getDaysRented() - DRAMA_FREE_DAYS) * DRAMA_EXTRA_DAY_RATE;
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
