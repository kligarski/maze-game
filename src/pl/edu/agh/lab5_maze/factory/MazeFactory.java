package pl.edu.agh.lab5_maze.factory;

import pl.edu.agh.lab5_maze.map_sites.Door;
import pl.edu.agh.lab5_maze.map_sites.Room;
import pl.edu.agh.lab5_maze.map_sites.Wall;

public class MazeFactory {
    public Room createRoom() {
        return new Room();
    }

    public Room createSpecialRoom() {
        return new Room();
    }

    public Wall createWall() {
        return new Wall('X');
    }

    public Wall createSpecialWall(Room room1, Room room2) {
        return new Wall('X');
    }

    public Door createDoor(Room room1, Room room2) {
        return new Door(room1, room2, true);
    }

    public Door createSpecialDoor(Room room1, Room room2) {
        return new Door(room1, room2, false);
    }
}
