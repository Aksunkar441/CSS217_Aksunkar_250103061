package Task11Adapter;

public class BoxAdapter implements IImperialBox {
    private final MetricBox box;

    public BoxAdapter(MetricBox box) { this.box = box; }
    @Override public double getWidthInches() { return box.getWidthCm() / 2.54; }
    @Override public double getHeightInches() { return box.getHeightCm() / 2.54; }
    @Override public double getAreaSquareInches() { return getWidthInches() * getHeightInches(); }
}
