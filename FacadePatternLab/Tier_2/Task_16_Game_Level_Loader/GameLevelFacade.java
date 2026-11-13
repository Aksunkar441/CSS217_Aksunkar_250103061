import java.util.Objects;

// Loading and unloading are kept together so cached and physics state do not leak between levels.
public class GameLevelFacade {
    private final AssetLoader assetLoader;
    private final TextureCache textureCache;
    private final PhysicsEngine physicsEngine;
    private final AudioMixer audioMixer;
    private final SaveStateReader saveStateReader;
    private boolean levelLoaded;

    public GameLevelFacade(AssetLoader assetLoader, TextureCache textureCache,
                           PhysicsEngine physicsEngine, AudioMixer audioMixer,
                           SaveStateReader saveStateReader) {
        this.assetLoader = Objects.requireNonNull(assetLoader);
        this.textureCache = Objects.requireNonNull(textureCache);
        this.physicsEngine = Objects.requireNonNull(physicsEngine);
        this.audioMixer = Objects.requireNonNull(audioMixer);
        this.saveStateReader = Objects.requireNonNull(saveStateReader);
    }

    public void loadLevel(int levelNumber, String saveSlot) {
        if (levelNumber <= 0 || saveSlot == null || saveSlot.isBlank()) {
            System.out.println("Level load rejected: provide a positive level number and save slot.");
            return;
        }
        String checkpoint = saveStateReader.readCheckpoint(saveSlot);
        if (checkpoint == null) {
            System.out.println("Level load rejected: save slot '" + saveSlot + "' was not found.");
            return;
        }
        if (levelLoaded) unloadCurrentLevel();

        System.out.println("\nLoading level " + levelNumber + "...");
        String geometry = assetLoader.loadGeometry(levelNumber);
        textureCache.loadTextures(levelNumber);
        physicsEngine.initialize(geometry);
        audioMixer.queueBackgroundMusic(levelNumber);
        System.out.println("Player checkpoint restored to " + checkpoint + ".");
        levelLoaded = true;
    }

    public void unloadCurrentLevel() {
        if (!levelLoaded) {
            System.out.println("No level is currently loaded.");
            return;
        }
        System.out.println("\nUnloading current level...");
        audioMixer.stopAll();
        textureCache.clear();
        physicsEngine.reset();
        levelLoaded = false;
        System.out.println("Level unloaded.");
    }
}
