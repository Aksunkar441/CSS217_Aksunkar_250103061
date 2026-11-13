public class UnitTestRunner {
    public StageResult run(String branchName) {
        if (branchName.contains("test-failure")) {
            return new StageResult(false, "Unit tests", "Unit tests failed: 2 of 18 tests did not pass.");
        }
        return new StageResult(true, "Unit tests", "All 18 unit tests passed.");
    }
}
