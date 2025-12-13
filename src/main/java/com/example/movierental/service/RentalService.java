package com.example.movierental.service;

import com.example.movierental.dto.RentalRequest;
import com.example.movierental.dto.RentalStatementResponse;
import com.example.movierental.dto.RentalStatementResponse.RentalLineItem;
import com.example.movierental.exception.ResourceNotFoundException;
import com.example.movierental.model.Customer;
import com.example.movierental.model.Movie;
import com.example.movierental.model.Rental;
import com.example.movierental.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final CustomerService customerService;
    private final MovieService movieService;
    private final PricingService pricingService;

    public RentalService(RentalRepository rentalRepository,
                         CustomerService customerService,
                         MovieService movieService,
                         PricingService pricingService) {
        this.rentalRepository = rentalRepository;
        this.customerService = customerService;
        this.movieService = movieService;
        this.pricingService = pricingService;
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id: " + id));
    }

    public Rental createRental(RentalRequest request) {
        customerService.getCustomerById(request.getCustomerId());
        movieService.getMovieById(request.getMovieId());

        Rental rental = new Rental(
                request.getCustomerId(),
                request.getMovieId(),
                request.getDaysRented()
        );
        return rentalRepository.save(rental);
    }

    public void deleteRental(Long id) {
        if (!rentalRepository.existsById(id)) {
            throw new ResourceNotFoundException("Rental not found with id: " + id);
        }
        rentalRepository.deleteById(id);
    }

    public RentalStatementResponse getStatementForCustomer(Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        List<Rental> rentals = rentalRepository.findByCustomerId(customerId);

        List<RentalLineItem> lineItems = new ArrayList<>();
        double totalAmount = 0;
        int totalPoints = 0;

        for (Rental rental : rentals) {
            Movie movie = movieService.getMovieById(rental.getMovieId());
            double amount = pricingService.calculateRentalAmount(movie, rental.getDaysRented());
            int points = pricingService.calculateFrequentRenterPoints(movie, rental.getDaysRented());

            lineItems.add(new RentalLineItem(movie.getTitle(), rental.getDaysRented(), amount));
            totalAmount += amount;
            totalPoints += points;
        }

        return new RentalStatementResponse(
                customer.getName(),
                lineItems,
                totalAmount,
                totalPoints
        );
    }
}
