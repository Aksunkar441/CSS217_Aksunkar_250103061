import java.nio.charset.StandardCharsets;

public class PdfConverter {
    // This is a small stand-in for a PDF library, keeping the lab focused on Facade.
    public byte[] convertToPdfBytes(String formattedText) {
        String content = "%PDF-1.4\n" + formattedText + "\n%%EOF";
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        System.out.println("Converted document to " + bytes.length + " PDF bytes.");
        return bytes;
    }
}
