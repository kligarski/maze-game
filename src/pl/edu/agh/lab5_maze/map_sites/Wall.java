package pl.edu.agh.lab5_maze.map_sites;

import pl.edu.agh.lab5_maze.Player;

public class Wall implements MapSite {
    protected char representation;

    public Wall(char representation) {
        this.representation = representation;
    }

    @Override
    public Room enter(Room previous, Player player) {
        return previous.enter(previous, player);
    }

    @Override
    public char getChar() {
        return representation;
    }
}
