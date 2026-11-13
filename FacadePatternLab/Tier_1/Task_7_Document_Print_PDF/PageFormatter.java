public class PageFormatter {
    public String formatForA4(String text) {
        System.out.println("Applied A4 page size and standard margins.");
        return "A4 DOCUMENT\nMargins: 2.5 cm\n\n" + text;
    }
}
