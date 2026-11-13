public class CompilerService {
    public StageResult compile(String branchName) {
        if (branchName.contains("compile-error")) {
            return new StageResult(false, "Compile", "Compilation failed: source contains a simulated syntax error.");
        }
        return new StageResult(true, "Compile", "Source compiled successfully.");
    }
}
