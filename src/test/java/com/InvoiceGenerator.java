package com;

public class InvoiceGenerator {
	private static final double COST_PER_KM_NORMAL = 10;
	private static final double COST_PER_MINUTE_NORMAL = 1;
	private static final double MINIMUM_FARE_NORMAL = 20;
	
	 public double calculateFare(double distance, int time, RideType rideType) {
	        double totalFare = (distance * COST_PER_KM_NORMAL) + (time * COST_PER_MINUTE_NORMAL);
	        return Math.max(totalFare, MINIMUM_FARE_NORMAL);
	    }
	 
	 public double calculateFare(Ride[] rides) {
		    double totalFare = 0;
		    for (Ride ride : rides) {
		        totalFare += calculateFare(ride.getDistance(), ride.getTime(), ride.getRideType());
		    }
		    return totalFare;
		}
	 public Invoice generateInvoice(Ride[] rides) {
		    double totalFare = calculateFare(rides);
		    return new Invoice(rides.length, totalFare, totalFare / rides.length);
		}



}
