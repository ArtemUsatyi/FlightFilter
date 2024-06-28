package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println(flights.size());
        List<Flight> flights1 = filterFlightBeforeCurrentTime(flights);
        System.out.println(flights1);
        System.out.println(flights1.size());
        List<Flight> flights2 = filterFlightAfterCurrentTime(flights);
        System.out.println(flights2);
        System.out.println(flights2.size());
        List<Flight> flights3 = filterFlightNotMoreTwoHoursOnEarth(flights);
        System.out.println(flights3);
        System.out.println(flights3.size());
    }

    public static List<Flight> filterFlightBeforeCurrentTime(List<Flight> flights) {
        List<Flight> resultFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (isFlightBeforeCurrentTime(flight)) {
                resultFlights.add(flight);
            }
        }
        return resultFlights;
    }

    public static List<Flight> filterFlightAfterCurrentTime(List<Flight> flights) {
        List<Flight> resultFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (isFlightAfterCurrentTime(flight)) {
                resultFlights.add(flight);
            }
        }
        return resultFlights;
    }

    public static List<Flight> filterFlightNotMoreTwoHoursOnEarth(List<Flight> flights) {
        if (flights == null) {
            return new ArrayList<>();
        }
        return flights.stream()
                .filter(flight -> flight.getSegments().size() <= 1 ||
                        IntStream.range(1, flight.getSegments().size())
                                .map(i -> Math.toIntExact(checkTimeDifference(flight.getSegments().get(i - 1).getArrivalDate(),
                                        flight.getSegments().get(i).getDepartureDate())))
                                .sum() <= 2)
                .collect(Collectors.toList());
    }

    private static long checkTimeDifference(LocalDateTime arrival, LocalDateTime departure) {
        return ChronoUnit.HOURS.between(arrival, departure);
    }

    private static boolean isFlightBeforeCurrentTime(Flight flight) {
        for (Segment segment : flight.getSegments()) {
            LocalDateTime departureDate = segment.getDepartureDate();
            LocalDateTime arrivalDate = segment.getArrivalDate();

            if (departureDate.isBefore(LocalDateTime.now()) && departureDate.isBefore(arrivalDate)) {
                if (arrivalDate.isAfter(LocalDateTime.now())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isFlightAfterCurrentTime(Flight flight) {
        for (Segment segment : flight.getSegments()) {
            LocalDateTime departureDate = segment.getDepartureDate();
            LocalDateTime arrivalDate = segment.getArrivalDate();

            if (departureDate.isBefore(LocalDateTime.now())) {
                if (arrivalDate.isBefore(LocalDateTime.now())) {
                    return true;
                }
            }
        }
        return false;
    }
}