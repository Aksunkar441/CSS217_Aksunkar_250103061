public class ArtifactPackager {
    public StageResult packageBuild(String branchName) {
        if (branchName.contains("package-error")) {
            return new StageResult(false, "Package", "Artifact packaging failed: required file is missing.");
        }
        return new StageResult(true, "Package", "Artifact " + branchName + "-build.zip was created.");
    }
}
