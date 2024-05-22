package pl.edu.agh.lab5_maze.factory;
import pl.edu.agh.lab5_maze.map_sites.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class BombedMazeFactory extends MazeFactory {
    private final String hint = "Find your way out of the maze, you may need bombs !";
    private BufferedImage bombWall;
    private BufferedImage bombedWall;

    private BombedMazeFactory() {
        super();
    }

    public static MazeFactory getInstance() {
        if (instance == null) {
            instance = new BombedMazeFactory();
        }
        return instance;
    }

    @Override
    protected void uploadTextures(){
        try {
            super.uploadTextures();
            bombWall = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("mapSite/bombWall.png")));
            bombedWall = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("mapSite/bombedWall.png")));
            this.wallImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("mapSite/wall.png")));
            this.roomImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("mapSite/bRoom.png")));
            this.doorImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("mapSite/door.png")));
        } catch(IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Room createRoom() {
        return new Room('✦', roomImage, hint);
    }

    @Override
    public Room createSpecialRoom() {
        return new BombedRoom('✦', roomImage, hint);
    }

    @Override
    public Wall createSpecialWall(Room room1, Room room2) {
        return new BombedWall(room1, room2, '✪', bombWall, bombedWall);
    }
}
