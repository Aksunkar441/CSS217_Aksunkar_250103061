package Task8Adapter;

public class EnterpriseTelemetryLogger {
    public void writeLog(int level, String appName, String message) {
        System.out.println("[" + appName + "][Level " + level + "] " + message);
    }
}
