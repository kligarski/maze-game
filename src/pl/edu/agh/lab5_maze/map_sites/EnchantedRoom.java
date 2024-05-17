package pl.edu.agh.lab5_maze.map_sites;

import pl.edu.agh.lab5_maze.Player;

public class EnchantedRoom extends Room {
    private boolean visited = false;

    @Override
    public Room enter(Room previous, Player player) {
        if (!visited) {
            visited = true;
        } else {
            player.kill();
        }
        return super.enter(previous, player);
    }

    @Override
    public char getChar() {
        return '▣';
    }
}
