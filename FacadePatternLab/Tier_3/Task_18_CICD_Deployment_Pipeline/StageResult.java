// Records make a compact immutable result for each pipeline step (Java 16+).
public record StageResult(boolean success, String stageName, String message) {
}
