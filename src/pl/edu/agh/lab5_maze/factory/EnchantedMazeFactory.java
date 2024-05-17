package pl.edu.agh.lab5_maze.factory;

import pl.edu.agh.lab5_maze.map_sites.EnchantedRoom;
import pl.edu.agh.lab5_maze.map_sites.Room;
import pl.edu.agh.lab5_maze.map_sites.Wall;

public class EnchantedMazeFactory extends MazeFactory {
    @Override
    public Wall createWall() {
        return new Wall('▧');
    }

    @Override
    public Wall createSpecialWall(Room room1, Room room2) {
        return new Wall('▧');
    }

    @Override
    public Room createRoom() {
        return new EnchantedRoom();
    }

    @Override
    public Room createSpecialRoom() {
        return createRoom();
    }
}
