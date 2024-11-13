package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RideRepository {
    private final Map<String, List<Ride>> userRides;

    public RideRepository() {
        this.userRides = new HashMap<>();
    }

    // Method to add rides for a user
    public void addRide(String userId, Ride ride) {
        this.userRides.computeIfAbsent(userId, k -> new ArrayList<>()).add(ride);
    }

    // Method to retrieve rides for a user
    public Ride[] getRides(String userId) {
        List<Ride> rides = userRides.get(userId);
        if (rides == null) {
            return new Ride[0]; // Return an empty array if no rides found for the user
        }
        return rides.toArray(new Ride[0]);
    }
}
