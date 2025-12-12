package example;

import org.junit.Test;

import static example.Movie.MovieType.*;
import static org.junit.Assert.*;

public class MovieTest {

    @Test
    public void testCreateRegularMovie() {
        Movie movie = new Movie("Rambo", REGULAR);

        assertEquals("Rambo", movie.getTitle());
        assertEquals(REGULAR, movie.getPriceCode());
    }

    @Test
    public void testCreateNewReleaseMovie() {
        Movie movie = new Movie("Avatar 2", NEW_RELEASE);

        assertEquals("Avatar 2", movie.getTitle());
        assertEquals(NEW_RELEASE, movie.getPriceCode());
    }

    @Test
    public void testCreateChildrensMovie() {
        Movie movie = new Movie("Finding Nemo", CHILDRENS);

        assertEquals("Finding Nemo", movie.getTitle());
        assertEquals(CHILDRENS, movie.getPriceCode());
    }

    @Test
    public void testMovieWithEmptyTitle() {
        Movie movie = new Movie("", REGULAR);

        assertEquals("", movie.getTitle());
        assertEquals(REGULAR, movie.getPriceCode());
    }

    @Test
    public void testMovieWithSpecialCharactersInTitle() {
        Movie movie = new Movie("Mission: Impossible", NEW_RELEASE);

        assertEquals("Mission: Impossible", movie.getTitle());
    }
}
