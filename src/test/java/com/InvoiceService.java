package com;

public class InvoiceService {
    private RideRepository rideRepository;

    public InvoiceService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    public Invoice getInvoice(String userId) {
        Ride[] rides = rideRepository.getRides(userId);
        return new InvoiceGenerator().generateInvoice(rides);
    }
}

