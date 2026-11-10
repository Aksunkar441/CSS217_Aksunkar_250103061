package Task18Adapter;

public class ThrottledApiAdapter implements IRateLimitedService {
    private final ThirdPartyApiServer server;
    private long lastInvocationTimestamp;

    public ThrottledApiAdapter(ThirdPartyApiServer server) { this.server = server; }
    public ThrottledApiAdapter() { this(new ThirdPartyApiServer()); }

    @Override
    public synchronized String getProtectedData() {
        long now = System.currentTimeMillis();
        long wait = 200 - (now - lastInvocationTimestamp);
        if (lastInvocationTimestamp != 0 && wait > 0) {
            try {
                Thread.sleep(wait);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Interrupted while throttling API request", exception);
            }
        }
        lastInvocationTimestamp = System.currentTimeMillis();
        return server.fetchData();
    }
}
