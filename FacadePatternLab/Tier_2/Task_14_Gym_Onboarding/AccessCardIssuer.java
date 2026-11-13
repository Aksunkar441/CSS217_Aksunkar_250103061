public class AccessCardIssuer {
    private int nextCardNumber = 70001;

    public String issueCard() { return "RFID-" + nextCardNumber++; }
}
