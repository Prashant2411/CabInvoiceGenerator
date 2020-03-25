package com.bridgelabz.cabinvoice.service;

import com.bridgelabz.cabinvoice.model.Ride;
import com.bridgelabz.cabinvoice.repository.RideRepository;

public class InvoiceSevice {
    
    private static final int COST_PER_KILOMETER = 10;
    private static final int COST_PER_MINUTE = 1;
    private static final int MINIMUM_FARE = 5;
    private RideRepository rideRepository;

    public InvoiceSevice(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    public double getTotalFare(double dist, int time) {
        double totalFare = (COST_PER_KILOMETER * dist) + (COST_PER_MINUTE * time);
        return Math.max(totalFare, MINIMUM_FARE);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride: rides){
            totalFare += this.getTotalFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare, totalFare/rides.length);
    }

    public void addRides(String userId, Ride[] ride1) {
           rideRepository.addRides(userId, ride1);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }
}
