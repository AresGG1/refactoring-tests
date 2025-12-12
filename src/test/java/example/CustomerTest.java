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

        assertTrue(statement.contains("**Amount owed:** 0.0"));
        assertTrue(statement.contains("**Frequent renter points:** 0"));
    }

    @Test
    public void testRegularMovieBasePricing() {
        Movie movie = new Movie("Rambo", REGULAR);
        Rental rental = new Rental(movie, 2);
        Customer customer = new Customer("John", List.of(rental));

        assertTrue(customer.statement().contains("**Amount owed:** 2.0"));
    }

    @Test
    public void testRegularMovieExtraDays() {
        Movie movie = new Movie("Rambo", REGULAR);
        Rental rental = new Rental(movie, 4);
        Customer customer = new Customer("John", List.of(rental));

        assertTrue(customer.statement().contains("**Amount owed:** 5.0"));
    }

    @Test
    public void testNewReleaseMoviePricing() {
        Movie movie = new Movie("Avatar 2", NEW_RELEASE);
        Rental rental = new Rental(movie, 3);
        Customer customer = new Customer("John", List.of(rental));

        assertTrue(customer.statement().contains("**Amount owed:** 9.0"));
    }

    @Test
    public void testChildrensMovieBasePricing() {
        Movie movie = new Movie("Nemo", CHILDRENS);
        Rental rental = new Rental(movie, 3);
        Customer customer = new Customer("John", List.of(rental));

        assertTrue(customer.statement().contains("**Amount owed:** 1.5"));
    }

    @Test
    public void testChildrensMovieExtraDays() {
        Movie movie = new Movie("Nemo", CHILDRENS);
        Rental rental = new Rental(movie, 5);
        Customer customer = new Customer("John", List.of(rental));

        assertTrue(customer.statement().contains("**Amount owed:** 4.5"));
    }

    @Test
    public void testMultipleRentals() {
        Movie m1 = new Movie("Rambo", REGULAR);
        Movie m2 = new Movie("Avatar", NEW_RELEASE);
        Customer customer = new Customer("John", List.of(
                new Rental(m1, 3),
                new Rental(m2, 2)
        ));

        assertTrue(customer.statement().contains("**Amount owed:** 9.5"));
    }

    @Test
    public void testFrequentRenterPointsBasic() {
        Movie movie = new Movie("Test", REGULAR);
        Customer customer = new Customer("John", List.of(new Rental(movie, 5)));

        assertTrue(customer.statement().contains("**Frequent renter points:** 1"));
    }

    @Test
    public void testFrequentRenterPointsBonusForNewRelease() {
        Movie movie = new Movie("Test", NEW_RELEASE);
        Customer customer = new Customer("John", List.of(new Rental(movie, 2)));

        assertTrue(customer.statement().contains("**Frequent renter points:** 2"));
    }

    @Test
    public void testNewReleaseOneDayNoBonus() {
        Movie movie = new Movie("Test", NEW_RELEASE);
        Customer customer = new Customer("John", List.of(new Rental(movie, 1)));

        assertTrue(customer.statement().contains("**Frequent renter points:** 1"));
    }

    @Test
    public void testComedyMovieBasePricing() {
        Movie movie = new Movie("Hangover", COMEDY);
        Rental rental = new Rental(movie, 2);
        Customer customer = new Customer("John", List.of(rental));

        assertTrue(customer.statement().contains("**Amount owed:** 2.5"));
    }

    @Test
    public void testDramaMovieExtraDays() {
        Movie movie = new Movie("Titanic", DRAMA);
        Rental rental = new Rental(movie, 3);
        Customer customer = new Customer("John", List.of(rental));

        assertTrue(customer.statement().contains("**Amount owed:** 7.0"));
    }

    @Test
    public void testMarkdownTableFormat() {
        Movie movie = new Movie("Test", REGULAR);
        Customer customer = new Customer("John", List.of(new Rental(movie, 1)));
        String statement = customer.statement();

        assertTrue(statement.contains("## Rental Record for John"));
        assertTrue(statement.contains("| Movie | Amount |"));
        assertTrue(statement.contains("|-------|-------:|"));
        assertTrue(statement.contains("| Test | 2.0 |"));
    }
}
