import java.util.Objects;

// A cross-campus registration is treated as one coordinated reservation:
// if transport cannot be secured, its workshop seat is released immediately.
public class EventLogisticsFacade {
    private final EligibilityChecker eligibility;
    private final QuotaManager quotas;
    private final WorkshopScheduler scheduler;
    private final ShuttleAllocator shuttles;
    private final BadgeGenerator badges;

    public EventLogisticsFacade(EligibilityChecker eligibility, QuotaManager quotas,
                                WorkshopScheduler scheduler, ShuttleAllocator shuttles,
                                BadgeGenerator badges) {
        this.eligibility = Objects.requireNonNull(eligibility);
        this.quotas = Objects.requireNonNull(quotas);
        this.scheduler = Objects.requireNonNull(scheduler);
        this.shuttles = Objects.requireNonNull(shuttles);
        this.badges = Objects.requireNonNull(badges);
    }

    public RegistrationResult registerAttendee(String studentId, String homeCampus,
                                                String eventCampus, String track) {
        Objects.requireNonNull(studentId, "studentId");
        Objects.requireNonNull(homeCampus, "homeCampus");
        Objects.requireNonNull(eventCampus, "eventCampus");
        Objects.requireNonNull(track, "track");
        System.out.println("\nRegistering " + studentId + " for " + track + " at " + eventCampus + ".");

        if (!eligibility.isEligible(studentId)) {
            return declined("Student eligibility check failed.");
        }
        if (!scheduler.isScheduled(eventCampus, track)) {
            return declined("No matching workshop is scheduled.");
        }
        if (!quotas.reserveSeat(eventCampus, track)) {
            return declined("Workshop quota is full.");
        }

        boolean needsShuttle = !homeCampus.equalsIgnoreCase(eventCampus);
        if (needsShuttle && !shuttles.reserveRoundTrip(homeCampus, eventCampus)) {
            quotas.releaseSeat(eventCampus, track);
            return declined("Shuttle is full; the reserved workshop seat was released.");
        }

        String badge = badges.generate(studentId, track);
        String travel = needsShuttle ? "Round-trip shuttle confirmed." : "No shuttle needed; student is on campus.";
        return new RegistrationResult(true, "Workshop seat confirmed. " + travel, badge, needsShuttle);
    }

    private RegistrationResult declined(String reason) {
        return new RegistrationResult(false, reason, null, false);
    }
}
