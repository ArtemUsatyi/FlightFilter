package com.gridnine.testing;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter flightFilter = new FlightFilterImpl();

        List<Flight> flightBeforeList = flightFilter.filterFlightBeforeCurrentTime(flights);
        System.out.println("Полеты до текущего момента времени:");
        System.out.println(flightBeforeList);
        System.out.println("------");

        List<Flight> flightAfterList = flightFilter.filterFlightAfterCurrentTime(flights);
        System.out.println("Полеты с датой прилёта раньше даты вылета:");
        System.out.println(flightAfterList);
        System.out.println("------");

        List<Flight> flightNotMoreTwoHoursList = flightFilter.filterFlightNotMoreTwoHoursOnEarth(flights);
        System.out.println("Полеты проведённое на Земле, не более двух часов");
        System.out.println(flightNotMoreTwoHoursList);
    }
}