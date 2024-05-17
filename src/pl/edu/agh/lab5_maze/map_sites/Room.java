package pl.edu.agh.lab5_maze.map_sites;

import pl.edu.agh.lab5_maze.Direction;
import pl.edu.agh.lab5_maze.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Room implements MapSite {
    private final Map<Direction, MapSite> sides;
    private final int roomNumber;
    private static int currentRoomsNumbers = 0;

    public Room() {
        this.roomNumber = currentRoomsNumbers++;
        this.sides = new HashMap<>();
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Optional<MapSite> getSide(Direction direction) {
        return Optional.ofNullable(sides.get(direction));
    }

    public void setSide(Direction direction, MapSite side) throws IllegalArgumentException {

        if(!sides.containsKey(direction)) {
            sides.put(direction, side);
        } else {
            throw new IllegalArgumentException("Side " + direction + " already exists");
        }
    }

    @Override
    public Room enter(Room previous, Player player) {
        return this;
    }

    @Override
    public char getChar() {
        return '□';
    }
}
