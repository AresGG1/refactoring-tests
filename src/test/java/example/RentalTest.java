package example;

import org.junit.Test;

import static example.Movie.MovieType.*;
import static org.junit.Assert.*;

public class RentalTest {

    @Test
    public void testCreateRental() {
        Movie movie = new Movie("Rambo", REGULAR);
        Rental rental = new Rental(movie, 3);

        assertEquals(movie, rental.getMovie());
        assertEquals(3, rental.getDaysRented());
    }

    @Test
    public void testRentalForOneDay() {
        Movie movie = new Movie("Avatar", NEW_RELEASE);
        Rental rental = new Rental(movie, 1);

        assertEquals(1, rental.getDaysRented());
    }

    @Test
    public void testRentalForMultipleDays() {
        Movie movie = new Movie("Finding Nemo", CHILDRENS);
        Rental rental = new Rental(movie, 7);

        assertEquals(7, rental.getDaysRented());
    }

    @Test
    public void testRentalReturnsCorrectMovie() {
        Movie movie = new Movie("The Matrix", REGULAR);
        Rental rental = new Rental(movie, 5);

        assertSame(movie, rental.getMovie());
        assertEquals("The Matrix", rental.getMovie().getTitle());
        assertEquals(REGULAR, rental.getMovie().getPriceCode());
    }

    @Test
    public void testRentalForZeroDays() {
        Movie movie = new Movie("Test", REGULAR);
        Rental rental = new Rental(movie, 0);

        assertEquals(0, rental.getDaysRented());
    }
}
