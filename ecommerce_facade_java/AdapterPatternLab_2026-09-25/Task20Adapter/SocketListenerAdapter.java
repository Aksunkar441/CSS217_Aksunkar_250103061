package Task20Adapter;

import java.nio.charset.StandardCharsets;

public class SocketListenerAdapter implements ILegacySocketListener {
    private final ISimplePacketHandler handler;

    public SocketListenerAdapter(ISimplePacketHandler handler) { this.handler = handler; }
    @Override public void onConnect() {}
    @Override public void onDisconnect() {}
    @Override public void onDataReceived(byte[] data) {
        handler.handlePacket(new String(data, StandardCharsets.UTF_8));
    }
    @Override public void onError(int errorCode) {}
    @Override public void onPing() {}
}
