package pl.edu.agh.lab5_maze;

public class Main {
    public static void main(String[] args) {
        Game game = new Game("/TestConfig.txt");
        game.run();
    }
}
