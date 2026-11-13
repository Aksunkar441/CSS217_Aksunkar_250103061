public class PrinterSpooler {
    public void queuePrintJob(String formattedText) {
        System.out.println("Print job added to the queue.");
        String preview = formattedText.substring(0, Math.min(60, formattedText.length()));
        System.out.println("Preview: " + preview.replace("\n", " "));
    }
}
