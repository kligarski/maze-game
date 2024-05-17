package pl.edu.agh.lab5_maze.map_sites;

import pl.edu.agh.lab5_maze.Player;

public class BombedRoom extends Room {
    private boolean bombPickedUp = false;

    @Override
    public Room enter(Room previous, Player player) {
        if (!bombPickedUp) {
            player.addSpecialItem();
            bombPickedUp = true;
        }
        return super.enter(previous, player);
    }

    @Override
    public char getChar() {
        return '⧇';
    }
}
