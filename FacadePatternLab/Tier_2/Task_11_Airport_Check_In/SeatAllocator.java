public class SeatAllocator {
    private int nextSeat = 12;

    public String assignNextSeat() {
        int row = nextSeat++;
        return row + "A";
    }
}
