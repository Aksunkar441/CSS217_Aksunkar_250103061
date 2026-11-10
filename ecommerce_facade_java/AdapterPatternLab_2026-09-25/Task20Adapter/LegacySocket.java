package Task20Adapter;

import java.nio.charset.StandardCharsets;

public class LegacySocket {
    public void registerListener(ILegacySocketListener listener) {
        listener.onDataReceived("HELLO_WORLD".getBytes(StandardCharsets.UTF_8));
    }
}
