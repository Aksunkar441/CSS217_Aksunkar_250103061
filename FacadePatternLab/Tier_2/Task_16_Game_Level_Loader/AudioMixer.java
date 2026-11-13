public class AudioMixer {
    public void queueBackgroundMusic(int levelNumber) {
        System.out.println("Background music queued for level " + levelNumber + ".");
    }

    public void stopAll() {
        System.out.println("All level audio stopped.");
    }
}
