package Task15Adapter;

public class StackAdapter implements ISimpleStack {
    private final LegacyArrayStack stack;

    public StackAdapter(LegacyArrayStack stack) { this.stack = stack; }
    public StackAdapter() { this(new LegacyArrayStack()); }
    @Override public void push(int value) { stack.pushInt(value); }
    @Override public int pop() { return stack.popInt(); }
    @Override public boolean isEmpty() { return stack.count() == 0; }
}
