public class Main {
    public static void main(String[] args) {
        HotelReceptionFacade reception = new HotelReceptionFacade(
                new RoomAvailability(), new BillingService(),
                new KeyCardEncoder(), new HousekeepingNotifier());

        int room = reception.checkInGuest("Alex Morgan", "DOUBLE", 3);
        reception.checkInGuest("Sam Lee", "PENTHOUSE", 2);
        if (room != -1) reception.checkOutGuest(room);
    }
}
