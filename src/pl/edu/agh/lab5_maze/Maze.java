package pl.edu.agh.lab5_maze;

import pl.edu.agh.lab5_maze.map_sites.Room;
import pl.edu.agh.lab5_maze.utils.Vec2d;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Maze {
    private final Map<Vec2d, Room> rooms;
    private Room initialRoom = null;
    private Room finalRoom = null;

    public Maze() {
        rooms = new HashMap<>();
    }

    public void setInitialRoom(Vec2d initialRoomPosition) {
        if (rooms.containsKey(initialRoomPosition)) {
            initialRoom = rooms.get(initialRoomPosition);
        } else {
            throw new IllegalArgumentException("Room is not a part of the maze.");
        }
    }

    public void addRoom(Vec2d position, Room room) {
        if (!rooms.containsKey(position)) {
            rooms.put(position, room);
        } else {
            throw new IllegalArgumentException("Room already exists");
        }
    }

    public Map<Vec2d, Room> getRooms() {
        return rooms;
    }

    public Optional<Room> getRoom(Vec2d position) {
        return Optional.ofNullable(rooms.get(position));
    }

    public void setFinalRoom(Vec2d finalRoomPosition) {
        if (rooms.containsKey(finalRoomPosition)) {
            this.finalRoom = rooms.get(finalRoomPosition);
        } else {
            throw new IllegalArgumentException("Room is not a part of the maze.");
        }
    }

    public Room getInitialRoom() {
        return initialRoom;
    }

    public Room getFinalRoom() {
        return finalRoom;
    }
}
