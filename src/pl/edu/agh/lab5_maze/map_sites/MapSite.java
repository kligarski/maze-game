package pl.edu.agh.lab5_maze.map_sites;

import pl.edu.agh.lab5_maze.Player;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface MapSite {
    Room enter(Room previous, Player player) throws IOException;
    char getChar();
    BufferedImage getImage();
}
