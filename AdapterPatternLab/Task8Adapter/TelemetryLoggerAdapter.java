package Task8Adapter;

public class TelemetryLoggerAdapter implements ISimpleLogger {
    private final EnterpriseTelemetryLogger logger;
    private final String appName;

    public TelemetryLoggerAdapter(EnterpriseTelemetryLogger logger, String appName) {
        this.logger = logger;
        this.appName = appName;
    }

    @Override public void info(String message) { logger.writeLog(1, appName, message); }
    @Override public void warn(String message) { logger.writeLog(2, appName, message); }
    @Override public void error(String message) { logger.writeLog(3, appName, message); }
}
