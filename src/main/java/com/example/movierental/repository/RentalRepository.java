package com.example.movierental.repository;

import com.example.movierental.model.Rental;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class RentalRepository {
    private final Map<Long, Rental> rentals = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Rental save(Rental rental) {
        if (rental.getId() == null) {
            rental.setId(idGenerator.getAndIncrement());
        }
        rentals.put(rental.getId(), rental);
        return rental;
    }

    public Optional<Rental> findById(Long id) {
        return Optional.ofNullable(rentals.get(id));
    }

    public List<Rental> findAll() {
        return new ArrayList<>(rentals.values());
    }

    public List<Rental> findByCustomerId(Long customerId) {
        return rentals.values().stream()
                .filter(rental -> rental.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        rentals.remove(id);
    }

    public boolean existsById(Long id) {
        return rentals.containsKey(id);
    }
}
