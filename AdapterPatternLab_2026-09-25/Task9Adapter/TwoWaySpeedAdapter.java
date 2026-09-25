package Task9Adapter;

public class TwoWaySpeedAdapter implements ISpeedInMph, ISpeedInKmh {
    private double speedInKmh;

    @Override public double getSpeedMph() { return speedInKmh * 0.621371; }
    @Override public void setSpeedMph(double mph) { speedInKmh = mph * 1.60934; }
    @Override public double getSpeedKmh() { return speedInKmh; }
    @Override public void setSpeedKmh(double kmh) { speedInKmh = kmh; }
}
