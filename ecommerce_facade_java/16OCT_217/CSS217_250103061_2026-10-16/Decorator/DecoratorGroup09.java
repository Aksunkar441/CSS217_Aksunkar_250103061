import java.util.Arrays;

class Task47_AudioFilter {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface AudioSignal {
        double[] process(double[] samples);
    }
    static class RawAudio implements AudioSignal {
        public double[] process(double[] s){
            return s.clone();
        }
    }
    abstract static class AudioDecorator implements AudioSignal {
        final AudioSignal inner;
        AudioDecorator(AudioSignal a){
            inner = a;
        }
    }
    static class GainDecorator extends AudioDecorator {
        GainDecorator(AudioSignal a){
            super(a);
        }
        public double[] process(double[] s){
            double[] o = inner.process(s);
            for (int i = 0; i < o.length; i++)o[i] *= 1.5;
            return o;
        }
    }
    static class HardClipDecorator extends AudioDecorator {
        HardClipDecorator(AudioSignal a){
            super(a);
        }
        public double[] process(double[] s){
            double[] o = inner.process(s);
            for (int i = 0; i < o.length; i++)o[i] = Math.max(-1, Math.min(1, o[i]));
            return o;
        }
    }
    static class MuteStartDecorator extends AudioDecorator {
        MuteStartDecorator(AudioSignal a){
            super(a);
        }
        public double[] process(double[] s){
            double[] o = inner.process(s);
            if (o.length > 0)o[0] = 0;
            return o;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        AudioSignal audio = new MuteStartDecorator(new HardClipDecorator(new GainDecorator(new RawAudio())));
        show(47, "Audio filter chain", Arrays.toString(audio.process(new double[]{.2, - .5, 1.2})));
    }
}
