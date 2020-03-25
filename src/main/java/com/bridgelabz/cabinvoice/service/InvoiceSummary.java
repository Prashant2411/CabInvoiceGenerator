package com.bridgelabz.cabinvoice.service;

public class InvoiceSummary {
    public int numOfRides;
    public double totalFare;
    public double averageFare;

    public InvoiceSummary(int numOfRides, double totalFare, double averageFare) {
        this.numOfRides = numOfRides;
        this.totalFare = totalFare;
        this.averageFare = averageFare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceSummary summary = (InvoiceSummary) o;
        return numOfRides == summary.numOfRides &&
                Double.compare(summary.totalFare, totalFare) == 0 &&
                Double.compare(summary.averageFare, averageFare) == 0;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "com.bridgelabz.cabinvoice.service.InvoiceSummary{" +
                "numOfRides=" + numOfRides +
                ", totalFare=" + totalFare +
                ", averageFare=" + averageFare +
                '}';
    }
}
