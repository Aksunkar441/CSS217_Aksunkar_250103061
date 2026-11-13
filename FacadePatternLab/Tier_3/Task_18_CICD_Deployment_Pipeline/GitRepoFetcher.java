public class GitRepoFetcher {
    public StageResult fetch(String branchName) {
        if (branchName == null || branchName.isBlank() || branchName.contains("missing")) {
            return new StageResult(false, "Fetch", "Branch was not found.");
        }
        return new StageResult(true, "Fetch", "Fetched branch " + branchName + ".");
    }
}
