package pl.edu.agh.lab5_maze.builder;
import pl.edu.agh.lab5_maze.utils.Vec2d;

import java.io.IOException;

public interface MazeBuilder {
    void addRoom(Vec2d position);
    void addSpecialRoom(Vec2d position);
    void addWall(Vec2d room1Position, Vec2d room2Position);
    void addSpecialWall(Vec2d room1Position, Vec2d room2Position) ;
    void addDoor(Vec2d room1Position, Vec2d room2Position);
    void addSpecialDoor(Vec2d room1Position, Vec2d room2Position);
    void setInitialRoom(Vec2d roomPosition);
    void setFinalRoom(Vec2d roomPosition);
}
