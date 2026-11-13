public class Main {
    public static void main(String[] args) {
        DocumentFacade document = new DocumentFacade(
                new SpellChecker(), new PageFormatter(), new PdfConverter(), new PrinterSpooler());
        String text = "This is teh first page of the lab document.";
        document.printCleanDocument(text);
        byte[] pdf = document.exportAsPdf(text, "lab-document.pdf");
        System.out.println("Export complete. Byte array size: " + pdf.length);
    }
}
