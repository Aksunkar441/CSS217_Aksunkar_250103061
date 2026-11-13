public class AssetLoader {
    public String loadGeometry(int levelNumber) {
        System.out.println("Loaded geometry for level " + levelNumber + ".");
        return "Level-" + levelNumber + "-geometry";
    }
}
