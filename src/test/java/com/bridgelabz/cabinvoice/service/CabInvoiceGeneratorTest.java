package com.bridgelabz.cabinvoice.service;

import com.bridgelabz.cabinvoice.model.Ride;
import com.bridgelabz.cabinvoice.repository.RideRepository;
import com.bridgelabz.cabinvoice.service.InvoiceSevice;
import com.bridgelabz.cabinvoice.service.InvoiceSummary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.when;

public class CabInvoiceGeneratorTest {
    InvoiceSevice cabInvoiceGenerator = null;

    @Mock
    RideRepository rideRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        cabInvoiceGenerator = new InvoiceSevice(new RideRepository());
    }

    @Test
    public void givenDistanceAndTime_shouldReturnTotalFare() {
        double dist = 2.0;
        int time = 5;
        double totalFare = cabInvoiceGenerator.getTotalFare(dist, time);
        Assert.assertEquals(25.0, totalFare, 0);
    }

    @Test
    public void givenLessDistanceAndTime_shouldReturnMinFare() {
        double dist = 0.1;
        int time = 1;
        double totalFare = cabInvoiceGenerator.getTotalFare(dist, time);
        Assert.assertEquals(5.0, totalFare, 0);
    }

    @Test
    public void givenMultipleRideData_shouldReturnInvoiceSummary() {
        Ride[] ride1 = {new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        InvoiceSummary summary = cabInvoiceGenerator.calculateFare(ride1);
        InvoiceSummary exceptedInvoiceSummary = new InvoiceSummary(2, 30.0, 15.0);
        Assert.assertEquals(exceptedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummary() {
        String userId = "sms@gmail.com";
        Ride[] ride1 = {new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        cabInvoiceGenerator.addRides(userId, ride1);
        InvoiceSummary summary = cabInvoiceGenerator.getInvoiceSummary(userId);
        System.out.println(summary);
        InvoiceSummary exceptedInvoiceSummary = new InvoiceSummary(2, 30.0, 15.0);
        Assert.assertEquals(exceptedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceMockito() {
        String userId = "sms@gmail.com";
        Ride[] ride1 = {new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        InvoiceSevice invoiceSevice = new InvoiceSevice(rideRepository);
        when(rideRepository.getRides(userId)).thenReturn(ride1);

        InvoiceSummary exceptedInvoiceSummary = new InvoiceSummary(2, 30.0, 15.0);
        InvoiceSummary summary = invoiceSevice.getInvoiceSummary(userId);
        Assert.assertEquals(exceptedInvoiceSummary, summary);
    }
}