public class Main {
    public static void main(String[] args) {
        StagingServer staging = new StagingServer();
        DeploymentFacade pipeline = new DeploymentFacade(
                new GitRepoFetcher(), new CompilerService(), new UnitTestRunner(),
                new ArtifactPackager(), staging);

        pipeline.deployBuild("main").printReport();
        pipeline.deployBuild("test-failure").printReport();
        pipeline.deployBuild("deploy-error").printReport();
    }
}
