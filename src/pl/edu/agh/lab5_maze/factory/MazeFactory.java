package pl.edu.agh.lab5_maze.factory;

import pl.edu.agh.lab5_maze.map_sites.Door;
import pl.edu.agh.lab5_maze.map_sites.Room;
import pl.edu.agh.lab5_maze.map_sites.Wall;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MazeFactory {
    protected static MazeFactory instance;
    protected BufferedImage wallImage;
    protected BufferedImage roomImage;
    protected BufferedImage doorImage;
    protected MazeFactory() {
        uploadTextures();
    }

    private final String hint = "Find your way out of the maze !";

    protected void uploadTextures(){
        try {
            this.wallImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("mapSite/wall.png")));
            this.roomImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("mapSite/room.png")));
            this.doorImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("mapSite/door.png")));
        } catch(IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static MazeFactory getInstance() {
        if (instance == null) {
            instance = new MazeFactory();
        }
        return instance;
    }

    public Room createRoom() {
        return new Room('✽', roomImage, hint);
    }

    public Room createSpecialRoom() {
        return new Room('✽', roomImage,hint);
    }

    public Wall createWall() {
        return new Wall('X',  wallImage);
    }

    public Wall createSpecialWall(Room room1, Room room2){
        return new Wall('X', wallImage);
    }

    public Door createDoor(Room room1, Room room2) {
        return new Door(room1, room2, true, '☐', doorImage );
    }

    public Door createSpecialDoor(Room room1, Room room2) {
        return new Door(room1, room2, false, '☐', doorImage);
    }
}
