import java.util.Locale;

class Task09_Speedometer {
    private static void show(int n, String title, Object result) {
        System.out.printf(Locale.ROOT, "%02d. %-38s %s%n", n, title, result);
    }
    interface MetricSpeedometer {
        double getSpeedKmh();
    }
    static class ImperialSpeedometer {
        double getSpeedMph(){
            return 60;
        }
    }
    static class SpeedAdapter implements MetricSpeedometer {
        private final ImperialSpeedometer s;
        SpeedAdapter(ImperialSpeedometer s){
            this.s = s;
        }
        public double getSpeedKmh(){
            return s.getSpeedMph() * 1.60934;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(9, "Speed adapter", new SpeedAdapter(new ImperialSpeedometer()).getSpeedKmh() + " km/h");
    }
}

class Task12_AudioPlayer {
    private static void show(int n, String title, Object result) {
        System.out.printf(Locale.ROOT, "%02d. %-38s %s%n", n, title, result);
    }
    interface MediaPlayer {
        void playAudio(String filename);
    }
    static class VlcEngine {
        void startPlayback(String filepath, int volume){
            System.out.printf("%s at volume %d", filepath, volume);
        }
    }
    static class VlcAdapter implements MediaPlayer {
        private final VlcEngine engine;
        VlcAdapter(VlcEngine e){
            engine = e;
        }
        public void playAudio(String f){
            engine.startPlayback(f, 80);
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(12, "VLC adapter", "playing below:");
        new VlcAdapter(new VlcEngine()).playAudio("song.mp3");
        System.out.println();
    }
}
