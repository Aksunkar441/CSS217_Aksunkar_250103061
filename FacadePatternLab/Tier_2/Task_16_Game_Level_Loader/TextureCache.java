import java.util.ArrayList;
import java.util.List;

public class TextureCache {
    private final List<String> cachedTextures = new ArrayList<>();

    public void loadTextures(int levelNumber) {
        cachedTextures.add("level-" + levelNumber + "-textures");
        System.out.println("Textures cached for level " + levelNumber + ".");
    }

    public void clear() {
        cachedTextures.clear();
        System.out.println("Texture cache cleared.");
    }
}
