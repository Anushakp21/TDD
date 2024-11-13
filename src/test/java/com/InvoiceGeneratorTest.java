package com;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InvoiceGeneratorTest {

    @Test
    void testCalculateFareForNormalRide() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        double distance = 5.0; // 5 km
        int time = 10;         // 10 minutes
        double fare = invoiceGenerator.calculateFare(distance, time, RideType.NORMAL);
        assertEquals(60, fare); // Expected fare: (5*10) + (10*1) = 60
    }

    @Test
    void testCalculateFareMinimum() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        double distance = 0.5; // 0.5 km
        int time = 1;          // 1 minute
        double fare = invoiceGenerator.calculateFare(distance, time, RideType.NORMAL);
        assertEquals(20, fare); // Expected minimum fare: 20
    }
    
    @Test
    void testCalculateFareForMultipleRides() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        Ride[] rides = {
            new Ride(5.0, 10, RideType.NORMAL), // 60
            new Ride(3.0, 5, RideType.NORMAL)   // 35
        };
        double totalFare = invoiceGenerator.calculateFare(rides);
        assertEquals(95, totalFare); // Expected total fare: 60 + 35 = 95
    }
    
    @Test
    void testGenerateEnhancedInvoice() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        Ride[] rides = {
            new Ride(5.0, 10, RideType.NORMAL),
            new Ride(3.0, 5, RideType.NORMAL)
        };
        Invoice invoice = invoiceGenerator.generateInvoice(rides);
        assertEquals(2, invoice.getTotalRides());
        assertEquals(95, invoice.getTotalFare());
        assertEquals(47.5, invoice.getAverageFarePerRide(), 0.01);
    }
    @Test
    void testGenerateInvoiceForUser() {
        RideRepository rideRepository = new RideRepository();
        rideRepository.addRide("user1", new Ride(5.0, 10, RideType.NORMAL));
        rideRepository.addRide("user1", new Ride(3.0, 5, RideType.NORMAL));

        InvoiceService invoiceService = new InvoiceService(rideRepository);
        Invoice invoice = invoiceService.getInvoice("user1");

        assertEquals(2, invoice.getTotalRides());
        assertEquals(95, invoice.getTotalFare());
        assertEquals(47.5, invoice.getAverageFarePerRide(), 0.01);
    }
}

