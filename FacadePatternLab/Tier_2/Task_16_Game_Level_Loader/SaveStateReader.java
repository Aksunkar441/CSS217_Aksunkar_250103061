import java.util.Map;

public class SaveStateReader {
    private final Map<String, String> checkpoints = Map.of(
            "slot1", "(12.0, 1.0, 7.5)",
            "slot2", "(3.5, 0.0, 18.0)",
            "autosave", "(0.0, 2.0, 0.0)");

    public String readCheckpoint(String saveSlot) {
        return checkpoints.get(saveSlot);
    }
}
