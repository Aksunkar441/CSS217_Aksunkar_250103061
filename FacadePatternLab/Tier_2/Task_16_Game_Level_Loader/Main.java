public class Main {
    public static void main(String[] args) {
        GameLevelFacade game = new GameLevelFacade(new AssetLoader(), new TextureCache(),
                new PhysicsEngine(), new AudioMixer(), new SaveStateReader());

        game.loadLevel(1, "slot1");
        game.loadLevel(2, "slot2"); // The first level is unloaded before the next one loads.
        game.unloadCurrentLevel();
    }
}
