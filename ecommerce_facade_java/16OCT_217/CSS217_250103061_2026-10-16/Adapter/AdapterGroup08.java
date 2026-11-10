import java.util.Arrays;

class Task45_RgbLuminance {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface GrayscalePixel {
        int getLuminance();
    }
    static class RgbColor {
        private final int r, g, b;
        RgbColor(int r, int g, int b){
            this.r = r;
            this.g = g;
            this.b = b;
        }
        int getRed(){
            return r;
        }
        int getGreen(){
            return g;
        }
        int getBlue(){
            return b;
        }
    }
    static class ColorAdapter implements GrayscalePixel {
        private final RgbColor c;
        ColorAdapter(RgbColor c){
            this.c = c;
        }
        public int getLuminance(){
            return (30 * c.getRed() + 59 * c.getGreen() + 11 * c.getBlue()) / 100;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(45, "RGB adapter", new ColorAdapter(new RgbColor(255, 0, 0)).getLuminance());
    }
}

class Task48_LifoStack {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface SimpleStack {
        void push(int val);
        int pop();
    }
    static class IntArrayBuffer {
        private int[] a = new int[8];
        private int size = 0;
        void append(int v){
            if (size == a.length)a = Arrays.copyOf(a, size * 2);
            a[size++] = v;
        }
        int removeAtEnd(){
            if (size == 0)throw new IllegalStateException("Stack is empty");
            return a[--size];
        }
    }
    static class StackAdapter implements SimpleStack {
        private final IntArrayBuffer b;
        StackAdapter(IntArrayBuffer b){
            this.b = b;
        }
        public void push(int v){
            b.append(v);
        }
        public int pop(){
            return b.removeAtEnd();
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        SimpleStack stack = new StackAdapter(new IntArrayBuffer());
        stack.push(10);
        stack.push(20);
        show(48, "Stack adapter", stack.pop());
    }
}
