import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Each step is recorded before deciding whether the next stage may run.
public class DeploymentFacade {
    private final GitRepoFetcher fetcher;
    private final CompilerService compiler;
    private final UnitTestRunner tests;
    private final ArtifactPackager packager;
    private final StagingServer staging;

    public DeploymentFacade(GitRepoFetcher fetcher, CompilerService compiler,
                            UnitTestRunner tests, ArtifactPackager packager,
                            StagingServer staging) {
        this.fetcher = Objects.requireNonNull(fetcher);
        this.compiler = Objects.requireNonNull(compiler);
        this.tests = Objects.requireNonNull(tests);
        this.packager = Objects.requireNonNull(packager);
        this.staging = Objects.requireNonNull(staging);
    }

    public DeploymentReport deployBuild(String branchName) {
        List<StageResult> results = new ArrayList<>();
        StageResult stage = fetcher.fetch(branchName);
        results.add(stage);
        if (!stage.success()) return report(false, results);

        stage = compiler.compile(branchName);
        results.add(stage);
        if (!stage.success()) return report(false, results);

        stage = tests.run(branchName);
        results.add(stage);
        if (!stage.success()) return report(false, results);

        stage = packager.packageBuild(branchName);
        results.add(stage);
        if (!stage.success()) return report(false, results);

        stage = staging.deploy(branchName + "-build.zip");
        results.add(stage);
        return report(stage.success(), results);
    }

    private DeploymentReport report(boolean success, List<StageResult> results) {
        return new DeploymentReport(success, results, staging.getCurrentHealthyVersion());
    }
}
