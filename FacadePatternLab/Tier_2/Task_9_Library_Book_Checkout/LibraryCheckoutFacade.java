import java.util.Objects;

// Coordinates the checks first, then updates the catalog and loan record.
public class LibraryCheckoutFacade {
    private final MemberValidator memberValidator;
    private final CatalogService catalog;
    private final FineCalculator fineCalculator;
    private final LoanRecorder loanRecorder;

    public LibraryCheckoutFacade(MemberValidator memberValidator, CatalogService catalog,
                                 FineCalculator fineCalculator, LoanRecorder loanRecorder) {
        this.memberValidator = Objects.requireNonNull(memberValidator);
        this.catalog = Objects.requireNonNull(catalog);
        this.fineCalculator = Objects.requireNonNull(fineCalculator);
        this.loanRecorder = Objects.requireNonNull(loanRecorder);
    }

    public boolean borrowBook(String studentId, String isbn) {
        if (studentId == null || isbn == null) {
            System.out.println("Borrowing rejected: student ID and ISBN are required.");
            return false;
        }
        if (!memberValidator.isActiveMember(studentId)) {
            System.out.println("Borrowing rejected: student is not an active member.");
            return false;
        }
        if (fineCalculator.getUnpaidFine(studentId) > 0.0) {
            System.out.printf("Borrowing rejected: unpaid fine is $%.2f.%n",
                    fineCalculator.getUnpaidFine(studentId));
            return false;
        }
        if (!catalog.isAvailable(isbn)) {
            System.out.println("Borrowing rejected: this book is unavailable.");
            return false;
        }
        if (loanRecorder.hasLoan(studentId, isbn)) {
            System.out.println("Borrowing rejected: this student already has this book.");
            return false;
        }

        catalog.checkout(isbn);
        loanRecorder.recordLoan(studentId, isbn);
        System.out.println("Book borrowed successfully. ISBN: " + isbn);
        return true;
    }

    public void returnBook(String studentId, String isbn) {
        if (studentId == null || isbn == null || !loanRecorder.closeLoan(studentId, isbn)) {
            System.out.println("Return not recorded: no matching active loan was found.");
            return;
        }
        catalog.checkIn(isbn);
        System.out.println("Book returned successfully. ISBN: " + isbn);
    }
}
