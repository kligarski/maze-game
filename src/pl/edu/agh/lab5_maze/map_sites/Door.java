package pl.edu.agh.lab5_maze.map_sites;

import pl.edu.agh.lab5_maze.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Door implements MapSite {
    private boolean isOpen;
    private final Room room1;
    private final Room room2;
    protected char representation;
    private BufferedImage image;


    public Door(Room room1, Room room2, boolean isOpen, char representation, BufferedImage image) {
        this.room1 = room1;
        this.room2 = room2;
        this.isOpen = isOpen;
        this.representation = representation;
        this.image = image;
    }

    public Door(Room room1, Room room2, char representation, BufferedImage image) {
        this(room1, room2, true, representation, image);
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public Room enter(Room previous, Player player) {
        if (isOpen) {
            if (previous == room1) {
                return room2.enter(previous, player);
            } else if (previous == room2) {
                return room1.enter(previous, player);
            }
        } else {
            if (previous == room1 || previous == room2) {
                return previous.enter(previous, player);
            }
        }
        throw new IllegalArgumentException("Unable to enter this door from previous room");
    }

    @Override
    public char getChar() {
        return representation;
    }

    @Override
    public BufferedImage getImage(){
        return image;
    }
}
