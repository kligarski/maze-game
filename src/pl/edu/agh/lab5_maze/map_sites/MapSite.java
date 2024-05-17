package pl.edu.agh.lab5_maze.map_sites;

import pl.edu.agh.lab5_maze.Player;

public interface MapSite {
    Room enter(Room previous, Player player);
    char getChar();
}
