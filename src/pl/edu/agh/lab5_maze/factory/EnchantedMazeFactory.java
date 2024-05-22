package pl.edu.agh.lab5_maze.factory;

import pl.edu.agh.lab5_maze.map_sites.EnchantedRoom;
import pl.edu.agh.lab5_maze.map_sites.Room;
import pl.edu.agh.lab5_maze.map_sites.Wall;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class EnchantedMazeFactory extends MazeFactory {
    private final String hint = "Find your way out of the maze, don't go back !";
    private EnchantedMazeFactory() {
        super();
    }
    @Override
    protected void uploadTextures(){
        try {
            super.uploadTextures();
            this.wallImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("mapSite/eWall.png")));
            this.roomImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("mapSite/eRoom.png")));
            this.doorImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("mapSite/door.png")));
        } catch(IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
    public static MazeFactory getInstance() {
        if (instance == null) {
            instance = new EnchantedMazeFactory();
        }
        return instance;
    }

    @Override
    public Wall createWall() {
        return new Wall('▧', wallImage);
    }

    @Override
    public Wall createSpecialWall(Room room1, Room room2) {
        return new Wall('▧', wallImage);

    }

    @Override
    public Room createRoom() {
        return new EnchantedRoom('✥', roomImage, hint);
    }

    @Override
    public Room createSpecialRoom() {
        return createRoom();
    }
}
