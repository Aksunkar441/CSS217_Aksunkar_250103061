public record EnergySummary(double demandKw, double solarKw, double batteryKw,
                            double gridKw, double shedLoadKw, double unmetDemandKw,
                            double batteryStateOfChargeKwh) {
    public void printSummary() {
        System.out.printf("\nEnergy summary — demand %.2f kW%n", demandKw);
        System.out.printf("Solar %.2f | Battery %.2f | Grid %.2f kW%n",
                solarKw, batteryKw, gridKw);
        System.out.printf("Shed load %.2f kW | Unmet demand %.2f kW%n",
                shedLoadKw, unmetDemandKw);
        System.out.printf("Battery state of charge %.2f kWh%n", batteryStateOfChargeKwh);
    }
}
