public class Main {
    public static void main(String[] args) {
        HomeTheaterFacade theater = new HomeTheaterFacade(
                new Amplifier(), new Projector(), new StreamingPlayer(), new TheaterLights());
        theater.watchMovie("The Grand Budapest Hotel");
        theater.endMovie();
        theater.watchMovie("Interstellar");
        theater.endMovie();
    }
}
