package pl.edu.agh.lab5_maze.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeFactoryTest {
    @Test
    void checkSingleton() {
        MazeFactory mazeFactory1 = BombedMazeFactory.getInstance();
        MazeFactory mazeFactory2 = MazeFactory.getInstance();

        assertEquals(mazeFactory1, mazeFactory2);
    }
}