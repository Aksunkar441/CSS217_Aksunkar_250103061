public class KeyCardEncoder {
    public String encodeKeyCard(String guestName, int roomNumber) {
        String cardId = "KEY-" + roomNumber + "-" + Math.abs(guestName.hashCode());
        System.out.println("Digital key card encoded: " + cardId);
        return cardId;
    }
}
