package Task20Adapter;

public interface ILegacySocketListener {
    void onConnect();
    void onDisconnect();
    void onDataReceived(byte[] data);
    void onError(int errorCode);
    void onPing();
}
