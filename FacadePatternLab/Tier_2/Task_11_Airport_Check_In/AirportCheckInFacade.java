import java.util.Objects;

public class AirportCheckInFacade {
    private final TicketVerifier ticketVerifier;
    private final SeatAllocator seatAllocator;
    private final BaggageHandler baggageHandler;
    private final BoardingPassGenerator passGenerator;

    public AirportCheckInFacade(TicketVerifier ticketVerifier, SeatAllocator seatAllocator,
                                BaggageHandler baggageHandler, BoardingPassGenerator passGenerator) {
        this.ticketVerifier = Objects.requireNonNull(ticketVerifier);
        this.seatAllocator = Objects.requireNonNull(seatAllocator);
        this.baggageHandler = Objects.requireNonNull(baggageHandler);
        this.passGenerator = Objects.requireNonNull(passGenerator);
    }

    public String completeCheckIn(String bookingRef, double bagWeightKg) {
        if (!ticketVerifier.isValidBooking(bookingRef)) {
            System.out.println("Check-in rejected: booking reference is not valid.");
            return null;
        }
        if (!baggageHandler.isWeightAllowed(bagWeightKg)) {
            System.out.println("Check-in rejected: checked bag must weigh between 0 and 23.0 kg.");
            return null;
        }

        String seat = seatAllocator.assignNextSeat();
        String baggageTag = baggageHandler.tagBag(bagWeightKg);
        String pass = passGenerator.generate(bookingRef, seat, baggageTag);
        System.out.println(pass);
        return pass;
    }
}
