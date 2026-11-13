public class Main {
    public static void main(String[] args) {
        AirportCheckInFacade airport = new AirportCheckInFacade(
                new TicketVerifier(), new SeatAllocator(),
                new BaggageHandler(), new BoardingPassGenerator());

        airport.completeCheckIn("AB12CD", 18.5);
        airport.completeCheckIn("BADREF", 16.0);
        airport.completeCheckIn("ZX90PQ", 25.0);
    }
}
