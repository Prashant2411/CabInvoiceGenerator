package com.bridgelabz.cabinvoice.repository;

import com.bridgelabz.cabinvoice.model.Ride;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RideRepository {
    Map<String, ArrayList<Ride>> userRides = new HashMap<>();

    public void addRides(String userId, Ride[] ride1) {
        ArrayList<Ride> rides = this.userRides.get(userId);
        if (rides == null){
            this.userRides.put(userId, new ArrayList<>((Arrays.asList(ride1))));
        } else {
            
        }
    }

    public Ride[] getRides(String userId) {
        return this.userRides.get(userId).toArray(new Ride[0]);
    }
}