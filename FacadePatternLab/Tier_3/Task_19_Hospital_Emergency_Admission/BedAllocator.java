public class BedAllocator {
    private int icuBeds = 1;
    private int monitoredErBays = 2;
    private int generalBeds = 4;

    public BedAssignment allocateIcu() {
        if (icuBeds > 0) {
            icuBeds--;
            return new BedAssignment("ICU", "");
        }
        if (monitoredErBays > 0) {
            monitoredErBays--;
            return new BedAssignment("MONITORED_ER_BAY",
                    "Warning: ICU is full; patient placed in a monitored ER bay.");
        }
        return new BedAssignment("WAITING_AREA", "Warning: no monitored bed is currently available.");
    }

    public BedAssignment allocateMonitoredEr() {
        if (monitoredErBays > 0) {
            monitoredErBays--;
            return new BedAssignment("MONITORED_ER_BAY", "");
        }
        if (generalBeds > 0) {
            generalBeds--;
            return new BedAssignment("GENERAL_BED", "Warning: monitored ER bays are full.");
        }
        return new BedAssignment("WAITING_AREA", "Warning: no bed is currently available.");
    }

    public BedAssignment allocateGeneral() {
        if (generalBeds > 0) {
            generalBeds--;
            return new BedAssignment("GENERAL_BED", "");
        }
        return new BedAssignment("WAITING_AREA", "Warning: general beds are full.");
    }
}
