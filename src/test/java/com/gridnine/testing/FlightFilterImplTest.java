package com.gridnine.testing;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightFilterImplTest {
    FlightFilter flightFilter = new FlightFilterImpl();

    Segment lastFlight = new Segment(LocalDateTime.now().minusHours(5), LocalDateTime.now());
    Segment normalFlight = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(3));
    Segment multiFlightPart1 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(2));
    Segment multiFlightPart2 = new Segment(LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(7));

    List<Segment> lastFlightList = List.of(lastFlight);
    List<Segment> normalFlightList = List.of(normalFlight);
    List<Segment> multiFlightList = List.of(multiFlightPart1, multiFlightPart2);

    Flight expectedLastFlight = new Flight(lastFlightList);
    Flight expectedNormalFlight = new Flight(normalFlightList);
    Flight expectedMultiFlight = new Flight(multiFlightList);
    List<Flight> flights = List.of(expectedLastFlight, expectedNormalFlight, expectedMultiFlight);

    @Test
    public void filterFlightAfterCurrentTime() {
        List<Flight> expectedFlight = List.of(expectedLastFlight);
        List<Flight> actualFlights = flightFilter.filterFlightAfterCurrentTime(flights);
        assertEquals(expectedFlight, actualFlights);
    }

    @Test
    public void filterFlightBeforeCurrentTime() {
        List<Flight> expectedFlight = List.of(expectedNormalFlight, expectedMultiFlight);
        List<Flight> actualFlights = flightFilter.filterFlightBeforeCurrentTime(flights);
        assertEquals(expectedFlight, actualFlights);
    }

    @Test
    public void filterFlightNotMoreTwoHoursOnEarth() {
        List<Flight> expectedFlight = List.of(expectedLastFlight, expectedNormalFlight);
        List<Flight> actualFlights = flightFilter.filterFlightNotMoreTwoHoursOnEarth(flights);
        assertEquals(expectedFlight, actualFlights);
    }
}
