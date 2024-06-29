package com.gridnine.testing;

import java.util.List;

public interface FlightFilter {

    List<Flight> filterFlightBeforeCurrentTime(List<Flight> flights);
    List<Flight> filterFlightAfterCurrentTime(List<Flight> flights);
    List<Flight> filterFlightNotMoreTwoHoursOnEarth(List<Flight> flights);
}
