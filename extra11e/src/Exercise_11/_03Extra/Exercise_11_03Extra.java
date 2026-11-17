package Exercise_11._03Extra;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Exercise_11_03Extra {
	public static void main(String[] args) {
		List<Flight> flights = new ArrayList<>();
		flights.add(new Flight("US230",
			new GregorianCalendar(2014, 4, 5, 5, 5, 0),
			new GregorianCalendar(2014, 4, 5, 6, 15, 0)));
		flights.add(new Flight("US235",
			new GregorianCalendar(2014, 4, 5, 6, 55, 0),
			new GregorianCalendar(2014, 4, 5, 7, 45, 0)));
		flights.add(new Flight("US237",
			new GregorianCalendar(2014, 4, 5, 9, 35, 0),
			new GregorianCalendar(2014, 4, 5, 12, 55, 0)));

		Itinerary itinerary = new Itinerary(flights);
		System.out.println(itinerary.getTotalTravelTime());
		System.out.println(itinerary.getTotalFlightTime());
	}
}

class Flight {
	private final String flightNo;
	private GregorianCalendar departureTime;
	private GregorianCalendar arrivalTime;

	public Flight(String flightNo, GregorianCalendar departureTime,
			GregorianCalendar arrivalTime) {
		this.flightNo = flightNo;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public GregorianCalendar getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(GregorianCalendar departureTime) {
		this.departureTime = departureTime;
	}

	public GregorianCalendar getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(GregorianCalendar arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getFlightTime() {
		return (int)((arrivalTime.getTimeInMillis()
			- departureTime.getTimeInMillis()) / (1000 * 60));
	}
}

class Itinerary {
	private final List<Flight> flights;

	public Itinerary(List<Flight> flights) {
		this.flights = flights;
	}

	public int getTotalTime() {
		long departure = flights.get(0).getDepartureTime().getTimeInMillis();
		long arrival = flights.get(flights.size() - 1)
			.getArrivalTime().getTimeInMillis();
		return (int)((arrival - departure) / (1000 * 60));
	}

	public int getTotalTravelTime() {
		return getTotalTime();
	}

	public int getTotalFlightTime() {
		int total = 0;
		for (Flight flight : flights) {
			total += flight.getFlightTime();
		}
		return total;
	}
}
