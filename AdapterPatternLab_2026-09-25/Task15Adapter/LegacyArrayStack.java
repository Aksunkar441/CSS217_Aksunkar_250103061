package Task15Adapter;

public class LegacyArrayStack {
    private final int[] storage = new int[5];
    private int pointer = 0;
    public void pushInt(int val) { if (pointer >= 5) throw new IllegalStateException("Stack Full"); storage[pointer++] = val; }
    public int popInt() { if (pointer <= 0) throw new IllegalStateException("Stack Empty"); return storage[--pointer]; }
    public int count() { return pointer; }
}
