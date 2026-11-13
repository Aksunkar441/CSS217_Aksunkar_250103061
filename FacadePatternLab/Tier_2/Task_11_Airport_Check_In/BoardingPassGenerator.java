public class BoardingPassGenerator {
    public String generate(String bookingRef, String seat, String baggageTag) {
        return "BOARDING PASS\n"
                + "Booking: " + bookingRef.toUpperCase() + "\n"
                + "Seat: " + seat + "\n"
                + "Checked bag: " + baggageTag;
    }
}
