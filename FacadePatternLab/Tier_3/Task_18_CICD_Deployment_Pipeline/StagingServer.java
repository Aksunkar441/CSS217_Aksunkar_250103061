public class StagingServer {
    private String currentHealthyVersion = "release-1.0";

    public StageResult deploy(String artifactName) {
        if (artifactName.contains("deploy-error")) {
            return new StageResult(false, "Deploy", "Staging rejected the artifact; health check failed.");
        }
        // Update only after the deployment health check succeeds.
        currentHealthyVersion = artifactName;
        return new StageResult(true, "Deploy", "Artifact deployed and health check passed.");
    }

    public String getCurrentHealthyVersion() {
        return currentHealthyVersion;
    }
}
