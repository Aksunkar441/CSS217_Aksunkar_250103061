import java.util.List;

public class DeploymentReport {
    private final boolean success;
    private final List<StageResult> executedStages;
    private final String deployedVersion;

    public DeploymentReport(boolean success, List<StageResult> executedStages, String deployedVersion) {
        this.success = success;
        this.executedStages = List.copyOf(executedStages);
        this.deployedVersion = deployedVersion;
    }

    public boolean isSuccess() { return success; }
    public List<StageResult> getExecutedStages() { return executedStages; }
    public String getDeployedVersion() { return deployedVersion; }

    public void printReport() {
        System.out.println("\nDeployment report: " + (success ? "SUCCESS" : "FAILED"));
        for (StageResult stage : executedStages) {
            System.out.printf("- %s: %s — %s%n", stage.stageName(),
                    stage.success() ? "OK" : "FAILED", stage.message());
        }
        System.out.println("Healthy staging version: " + deployedVersion);
    }
}
