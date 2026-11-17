public class Main {
    public static void main(String[] args) {
// Notice: Main knows NOTHING about the 5 underlying subsystems.
        SeatInventorySystem seatInventorySystem = new SeatInventorySystem();
        BaggagePricingEngine baggagePricingEngine = new BaggagePricingEngine();
        CurrencyConverter currencyConverter = new CurrencyConverter();
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        TicketIssuer ticketIssuer = new TicketIssuer();

        FlightBookingFacade bookingFacade = new FlightBookingFacade(
                seatInventorySystem,
                baggagePricingEngine,
                currencyConverter,
                paymentProcessor,
                ticketIssuer
                );



        System.out.println("=== TEST 1: Standard Successful Booking ===");
        String ticket1 = bookingFacade.bookFlight(
                "Amina Suleimenova",
                "KC101",
                "14A",
                320.00,
                2,
                23.0,
                "EUR",
                "4532015112830366"
        );
        System.out.println("Result 1: " + ticket1 + " ");
                System.out.println("=== TEST 2: Blocked Crew Seat (Row 13) ===");
        String ticket2 = bookingFacade.bookFlight(
                "Daniyar Kassymov",
                "KC101",
                "13C",
                320.00,
                1,
                15.0,
                "USD",
                "4532015112830366"
        );
        System.out.println("Result 2: " + ticket2 + " ");
                System.out.println("=== TEST 3: Payment Failure & Automatic Seat Rollback ===");
        String ticket3 = bookingFacade.bookFlight(
                "Timur Bekov",
                "KC101",
                "07D",
                320.00,
                1,
                18.0,
                "KZT",
                "9999" // Invalid short card number triggers rollback
                );

        System.out.println("Result 3: " + ticket3 + " ");
    }
}