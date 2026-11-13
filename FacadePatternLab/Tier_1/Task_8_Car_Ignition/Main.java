public class Main {
    public static void main(String[] args) {
        CarIgnitionFacade car = new CarIgnitionFacade(
                new Battery(), new FuelPump(), new StarterMotor(), new DashboardDisplay());
        car.startEngine();
        System.out.println("Taking a short drive...");
        car.stopEngine();
    }
}
