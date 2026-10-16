
class Task27_ContractorStaff {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface StaffMember {
        String getFullName();
        double getAnnualSalary();
    }
    static class ContractorRecord {
        private final String first, last;
        private final double monthly;
        ContractorRecord(String f, String l, double m){
            first = f;
            last = l;
            monthly = m;
        }
        String getFirstName(){
            return first;
        }
        String getLastName(){
            return last;
        }
        double getMonthlyPay(){
            return monthly;
        }
    }
    static class ContractorAdapter implements StaffMember {
        private final ContractorRecord c;
        ContractorAdapter(ContractorRecord c){
            this.c = c;
        }
        public String getFullName(){
            return c.getFirstName() + " "+ c.getLastName();
        }
        public double getAnnualSalary(){
            return c.getMonthlyPay() * 12;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        StaffMember staff = new ContractorAdapter(new ContractorRecord("Ada", "Lovelace", 5000));
        show(27, "Contractor adapter", staff.getFullName() + " / "+ staff.getAnnualSalary());
    }
}

class Task30_BatteryMonitor {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface PercentageBattery {
        int getBatteryPercent();
    }
    static class VoltageBattery {
        private final double v;
        VoltageBattery(double v){
            this.v = v;
        }
        double getVoltage(){
            return v;
        }
    }
    static class BatteryAdapter implements PercentageBattery {
        private final VoltageBattery b;
        BatteryAdapter(VoltageBattery b){
            this.b = b;
        }
        public int getBatteryPercent(){
            return Math.max(0, Math.min(100, (int)Math.round(((b.getVoltage() - 3.0) / 1.2) * 100)));
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(30, "Battery adapter", new BatteryAdapter(new VoltageBattery(3.75)).getBatteryPercent() + "%");
    }
}
