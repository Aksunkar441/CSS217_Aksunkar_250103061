package Task13Adapter;

public class SmsNotificationAdapter implements IPushNotifier {
    private final ThirdPartySmsProvider provider;

    public SmsNotificationAdapter(ThirdPartySmsProvider provider) { this.provider = provider; }
    public SmsNotificationAdapter() { this(new ThirdPartySmsProvider()); }

    @Override
    public void notify(AlertMessage alert) {
        if (alert == null) throw new IllegalArgumentException("alert must not be null");
        String phone = alert.userPhone();
        if (!phone.startsWith("+")) phone = "+" + phone;
        provider.sendSms(phone, "[" + alert.title() + "] " + alert.body());
    }
}
