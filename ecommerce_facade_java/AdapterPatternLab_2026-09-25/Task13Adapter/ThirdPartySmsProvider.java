package Task13Adapter;

public class ThirdPartySmsProvider {
    public void sendSms(String internationalPhoneNumber, String text) {
        System.out.println("SMS to " + internationalPhoneNumber + ": " + text);
    }
}
