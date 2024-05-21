package pl.edu.agh.lab5_maze.map_sites;

import pl.edu.agh.lab5_maze.Direction;
import pl.edu.agh.lab5_maze.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Room implements MapSite {
    private final Map<Direction, MapSite> sides;
    private final int roomNumber;
    private static int currentRoomsNumbers = 0;
    protected char representation;
    private final BufferedImage image;
    private final String hint;

    public Room(char representation,BufferedImage image, String hint) {
        this.roomNumber = currentRoomsNumbers++;
        this.sides = new HashMap<>();
        this.representation = representation;
        this.image = image;
        this.hint = hint;
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
    public char getChar()  {
        return representation;
    }

    @Override
    public BufferedImage getImage(){
        return image;
    }

    public String getHint() {
        return hint;
    }
}
