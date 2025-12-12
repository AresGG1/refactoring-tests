package example;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static example.MovieType.*;
import static org.junit.Assert.*;

public class CustomerTest {

    @Test
    public void testCustomerName() {
        Customer customer = new Customer("John", Collections.emptyList());
        assertEquals("John", customer.getName());
    }

    @Test
    public void testEmptyRentals() {
        Customer customer = new Customer("Jane", Collections.emptyList());
        String statement = customer.statement();

        assertTrue(statement.contains("Amount owed is 0.0"));
        assertTrue(statement.contains("You earned 0 frequent renter points"));
    }

    @Test
    public void testRegularMovieBasePricing() {
        Movie movie = new Movie("Rambo", REGULAR);
        Rental rental = new Rental(movie, 2);
        Customer customer = new Customer("John", List.of(rental));

        assertTrue(customer.statement().contains("Amount owed is 2.0"));
    }

    @Test
    public void testRegularMovieExtraDays() {
        // Regular: $2 + (4-2) * $1.5 = $5.0
        Movie movie = new Movie("Rambo", REGULAR);
        Rental rental = new Rental(movie, 4);
        Customer customer = new Customer("John", List.of(rental));

        assertTrue(customer.statement().contains("Amount owed is 5.0"));
    }

    @Test
    public void testNewReleaseMoviePricing() {
        // New Release: 3 * $3 = $9
        Movie movie = new Movie("Avatar 2", NEW_RELEASE);
        Rental rental = new Rental(movie, 3);
        Customer customer = new Customer("John", List.of(rental));

        assertTrue(customer.statement().contains("Amount owed is 9.0"));
    }

    @Test
    public void testChildrensMovieBasePricing() {
        // Children's: $1.5 base for 3 days
        Movie movie = new Movie("Nemo", CHILDRENS);
        Rental rental = new Rental(movie, 3);
        Customer customer = new Customer("John", List.of(rental));

        assertTrue(customer.statement().contains("Amount owed is 1.5"));
    }

    @Test
    public void testChildrensMovieExtraDays() {
        Movie movie = new Movie("Nemo", CHILDRENS);
        Rental rental = new Rental(movie, 5);
        Customer customer = new Customer("John", List.of(rental));

        assertTrue(customer.statement().contains("Amount owed is 4.5"));
    }

    @Test
    public void testMultipleRentals() {
        Movie m1 = new Movie("Rambo", REGULAR);
        Movie m2 = new Movie("Avatar", NEW_RELEASE);
        Customer customer = new Customer("John", List.of(
                new Rental(m1, 3),
                new Rental(m2, 2)
        ));

        assertTrue(customer.statement().contains("Amount owed is 9.5"));
    }

    @Test
    public void testFrequentRenterPointsBasic() {
        // Regular movie: 1 point
        Movie movie = new Movie("Test", REGULAR);
        Customer customer = new Customer("John", List.of(new Rental(movie, 5)));

        assertTrue(customer.statement().contains("You earned 1 frequent renter points"));
    }

    @Test
    public void testFrequentRenterPointsBonusForNewRelease() {
        // New Release > 1 day: 2 points (1 base + 1 bonus)
        Movie movie = new Movie("Test", NEW_RELEASE);
        Customer customer = new Customer("John", List.of(new Rental(movie, 2)));

        assertTrue(customer.statement().contains("You earned 2 frequent renter points"));
    }

    @Test
    public void testNewReleaseOneDayNoBonus() {
        Movie movie = new Movie("Test", NEW_RELEASE);
        Customer customer = new Customer("John", List.of(new Rental(movie, 1)));

        assertTrue(customer.statement().contains("You earned 1 frequent renter points"));
    }
}
