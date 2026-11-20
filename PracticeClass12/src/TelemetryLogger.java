// Subsystem 5: Records audit-trail state transitions for food-safety and maintenance logs.
public class TelemetryLogger {
    // Counter incremented on every log entry to preserve strict chronological order.
    private int sequenceNumber = 0;

    // Appends a formatted event line to standard output.
    public void logState(String subsystemTag, String eventMessage) {
        sequenceNumber++;
        System.out.printf("[LOG #%02d] [%-14s] %s%n", sequenceNumber, subsystemTag, eventMessage);
    }

    // Resets sequence counter between independent roast batches if desired.
    public void resetBatchLog() {
        this.sequenceNumber = 0;
        System.out.println("[TelemetryLogger] Batch log sequence reset.");
    }
}
