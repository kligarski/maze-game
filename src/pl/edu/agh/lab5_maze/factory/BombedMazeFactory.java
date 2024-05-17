package pl.edu.agh.lab5_maze.factory;

import pl.edu.agh.lab5_maze.map_sites.BombedRoom;
import pl.edu.agh.lab5_maze.map_sites.BombedWall;
import pl.edu.agh.lab5_maze.map_sites.Room;
import pl.edu.agh.lab5_maze.map_sites.Wall;

public class BombedMazeFactory extends MazeFactory {
    @Override
    public Room createSpecialRoom() {
        return new BombedRoom();
    }

    @Override
    public Wall createSpecialWall(Room room1, Room room2) {
        return new BombedWall(room1, room2);
    }
}
