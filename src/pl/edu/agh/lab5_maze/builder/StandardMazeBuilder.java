package pl.edu.agh.lab5_maze.builder;

import pl.edu.agh.lab5_maze.Direction;
import pl.edu.agh.lab5_maze.Maze;
import pl.edu.agh.lab5_maze.factory.MazeFactory;
import pl.edu.agh.lab5_maze.map_sites.Door;
import pl.edu.agh.lab5_maze.map_sites.Room;
import pl.edu.agh.lab5_maze.map_sites.Wall;
import pl.edu.agh.lab5_maze.utils.Vec2d;

import java.util.Optional;

public class StandardMazeBuilder implements MazeBuilder {
    private final MazeFactory mazeFactory;
    private final Maze maze;

    private Vec2d initialRoomPosition = null;
    private Vec2d finalRoomPosition = null;

    public StandardMazeBuilder(MazeFactory mazeFactory) {
        this.mazeFactory = mazeFactory;
        this.maze = new Maze();
    }

    @Override
    public void addRoom(Vec2d position) {
        maze.addRoom(position, mazeFactory.createRoom());
    }

    @Override
    public void addSpecialRoom(Vec2d position) {
        maze.addRoom(position, mazeFactory.createSpecialRoom());
    }

    private Direction commonWall(Vec2d room1Position, Vec2d room2Position) {
        Optional<Direction> direction = Vec2d.getDirection(room1Position, room2Position);
        if (direction.isPresent()) {
            return direction.get();
        } else {
            throw new IllegalArgumentException("Rooms must be next to each other");
        }
    }

    @Override
    public void addWall(Vec2d room1Position, Vec2d room2Position) {
        Optional<Room> room1 = maze.getRoom(room1Position);
        Optional<Room> room2 = maze.getRoom(room2Position);

        if (room1.isEmpty()) {
            throw new IllegalArgumentException("room1Position must be a valid room position");
        }

        Wall wall = mazeFactory.createWall();

        room1.get().setSide(commonWall(room1Position, room2Position), wall);
        room2.ifPresent(room -> room.setSide(commonWall(room2Position, room1Position), wall));
    }

    @Override
    public void addSpecialWall(Vec2d room1Position, Vec2d room2Position) {
        Optional<Room> room1 = maze.getRoom(room1Position);
        Optional<Room> room2 = maze.getRoom(room2Position);

        if (room1.isEmpty() || room2.isEmpty()) {
            throw new IllegalArgumentException("Room positions must be valid room positions");
        }

        Wall wall = mazeFactory.createSpecialWall(room1.get(), room2.get());

        room1.get().setSide(commonWall(room1Position, room2Position), wall);
        room2.get().setSide(commonWall(room2Position, room1Position), wall);
    }

    @Override
    public void addDoor(Vec2d room1Position, Vec2d room2Position) {
        Optional<Room> room1 = maze.getRoom(room1Position);
        Optional<Room> room2 = maze.getRoom(room2Position);

        if (room1.isEmpty() || room2.isEmpty()) {
            throw new IllegalArgumentException("Room positions must be valid room positions");
        }

        Door door = mazeFactory.createDoor(room1.get(), room2.get());

        room1.get().setSide(commonWall(room1Position, room2Position), door);
        room2.get().setSide(commonWall(room2Position, room1Position), door);
    }

    @Override
    public void addSpecialDoor(Vec2d room1Position, Vec2d room2Position) {
        Optional<Room> room1 = maze.getRoom(room1Position);
        Optional<Room> room2 = maze.getRoom(room2Position);

        if (room1.isEmpty() || room2.isEmpty()) {
            throw new IllegalArgumentException("Room positions must be valid room positions");
        }

        Door door = mazeFactory.createSpecialDoor(room1.get(), room2.get());

        room1.get().setSide(commonWall(room1Position, room2Position), door);
        room2.get().setSide(commonWall(room2Position, room1Position), door);
    }

    @Override
    public void setInitialRoom(Vec2d roomPosition) {
        initialRoomPosition = roomPosition;
    }

    @Override
    public void setFinalRoom(Vec2d roomPosition) {
        finalRoomPosition = roomPosition;
    }

    public Maze getMaze() {
        if (initialRoomPosition == null) {
            throw new RuntimeException("No initial room set.");
        }

        if (finalRoomPosition == null) {
            throw new RuntimeException("No final room set.");
        }

        if (initialRoomPosition == finalRoomPosition) {
            throw new RuntimeException("Initial position must be different than final position.");
        }

        maze.setInitialRoom(initialRoomPosition);
        maze.setFinalRoom(finalRoomPosition);

        return maze;
    }
}
