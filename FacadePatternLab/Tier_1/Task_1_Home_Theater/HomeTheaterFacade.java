import java.util.Objects;

// The facade keeps the movie setup and cleanup in one convenient place.
public class HomeTheaterFacade {
    private final Amplifier amplifier;
    private final Projector projector;
    private final StreamingPlayer player;
    private final TheaterLights lights;

    public HomeTheaterFacade(Amplifier amplifier, Projector projector,
                             StreamingPlayer player, TheaterLights lights) {
        this.amplifier = Objects.requireNonNull(amplifier);
        this.projector = Objects.requireNonNull(projector);
        this.player = Objects.requireNonNull(player);
        this.lights = Objects.requireNonNull(lights);
    }

    public void watchMovie(String movie) {
        System.out.println("\nPreparing to watch: " + movie);
        lights.dim(10);
        projector.on();
        projector.wideScreenMode();
        amplifier.on();
        amplifier.setVolume(5);
        player.on();
        player.play(movie);
    }

    public void endMovie() {
        System.out.println("\nEnding movie session.");
        player.stop();
        player.off();
        amplifier.off();
        projector.off();
        lights.on();
    }
}
