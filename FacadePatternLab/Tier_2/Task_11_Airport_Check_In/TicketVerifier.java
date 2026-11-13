import java.util.Set;

public class TicketVerifier {
    private final Set<String> validBookingReferences = Set.of("AB12CD", "ZX90PQ", "LM45NO");

    public boolean isValidBooking(String bookingRef) {
        return bookingRef != null && validBookingReferences.contains(bookingRef.toUpperCase());
    }
}
