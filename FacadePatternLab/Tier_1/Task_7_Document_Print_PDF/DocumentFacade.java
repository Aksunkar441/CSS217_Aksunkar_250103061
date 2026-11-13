import java.util.Objects;

// Printing and exporting share the same spelling and page-formatting steps.
public class DocumentFacade {
    private final SpellChecker spellChecker;
    private final PageFormatter formatter;
    private final PdfConverter converter;
    private final PrinterSpooler printer;

    public DocumentFacade(SpellChecker spellChecker, PageFormatter formatter,
                          PdfConverter converter, PrinterSpooler printer) {
        this.spellChecker = Objects.requireNonNull(spellChecker);
        this.formatter = Objects.requireNonNull(formatter);
        this.converter = Objects.requireNonNull(converter);
        this.printer = Objects.requireNonNull(printer);
    }

    public void printCleanDocument(String text) {
        String cleanText = spellChecker.check(Objects.requireNonNull(text, "text"));
        printer.queuePrintJob(formatter.formatForA4(cleanText));
    }

    public byte[] exportAsPdf(String text, String fileName) {
        Objects.requireNonNull(fileName, "fileName");
        System.out.println("\nExporting " + fileName + ".");
        String cleanText = spellChecker.check(Objects.requireNonNull(text, "text"));
        return converter.convertToPdfBytes(formatter.formatForA4(cleanText));
    }
}
