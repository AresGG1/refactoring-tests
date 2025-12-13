package com.example.movierental.controller;

import com.example.movierental.dto.RentalRequest;
import com.example.movierental.dto.RentalStatementResponse;
import com.example.movierental.model.Rental;
import com.example.movierental.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    public Rental getRentalById(@PathVariable Long id) {
        return rentalService.getRentalById(id);
    }

    @PostMapping
    public ResponseEntity<Rental> createRental(@Valid @RequestBody RentalRequest request) {
        Rental created = rentalService.createRental(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/statement/{customerId}")
    public RentalStatementResponse getStatement(@PathVariable Long customerId) {
        return rentalService.getStatementForCustomer(customerId);
    }
}
