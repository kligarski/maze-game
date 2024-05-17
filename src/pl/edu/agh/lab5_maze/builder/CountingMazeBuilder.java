package pl.edu.agh.lab5_maze.builder;

import pl.edu.agh.lab5_maze.utils.Vec2d;

public class CountingMazeBuilder implements MazeBuilder {
    private int rooms = 0;
    private int walls = 0;
    private int doors = 0;

    public String getCounts() {
        return "Rooms: " + rooms + "\n" +
               "Walls: " + walls + "\n" +
               "Doors: " + doors;
    }

    @Override
    public void addRoom(Vec2d position) {
        ++rooms;
    }

    @Override
    public void addSpecialRoom(Vec2d position) {
        ++rooms;
    }

    @Override
    public void addWall(Vec2d room1Position, Vec2d room2Position) {
        ++walls;
    }

    @Override
    public void addSpecialWall(Vec2d room1Position, Vec2d room2Position) {
        ++walls;
    }

    @Override
    public void addDoor(Vec2d room1Position, Vec2d room2Position) {
        ++doors;
    }

    @Override
    public void addSpecialDoor(Vec2d room1Position, Vec2d room2Position) {
        ++doors;
    }

    @Override
    public void setInitialRoom(Vec2d roomPosition) {
        return;
    }

    @Override
    public void setFinalRoom(Vec2d roomPosition) {
        return;
    }
}
