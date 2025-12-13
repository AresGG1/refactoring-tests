package com.example.movierental.repository;

import com.example.movierental.model.Movie;
import com.example.movierental.model.MovieType;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MovieRepository {
    private final Map<Long, Movie> movies = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public MovieRepository() {
        save(new Movie("The Matrix", MovieType.REGULAR));
        save(new Movie("Dune: Part Two", MovieType.NEW_RELEASE));
        save(new Movie("Finding Nemo", MovieType.CHILDRENS));
        save(new Movie("The Hangover", MovieType.COMEDY));
        save(new Movie("The Shawshank Redemption", MovieType.DRAMA));
    }

    public Movie save(Movie movie) {
        if (movie.getId() == null) {
            movie.setId(idGenerator.getAndIncrement());
        }
        movies.put(movie.getId(), movie);
        return movie;
    }

    public Optional<Movie> findById(Long id) {
        return Optional.ofNullable(movies.get(id));
    }

    public List<Movie> findAll() {
        return new ArrayList<>(movies.values());
    }

    public void deleteById(Long id) {
        movies.remove(id);
    }

    public boolean existsById(Long id) {
        return movies.containsKey(id);
    }
}
