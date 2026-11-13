public class Main {
    public static void main(String[] args) {
        LibraryCheckoutFacade library = new LibraryCheckoutFacade(
                new MemberValidator(), new CatalogService(),
                new FineCalculator(), new LoanRecorder());

        library.borrowBook("S1001", "9780134685991");
        library.borrowBook("S1002", "9780596007126"); // This student has an unpaid fine.
        library.returnBook("S1001", "9780134685991");
        library.borrowBook("S1003", "9780134685991");
    }
}
