package pl.edu.agh.lab5_maze.map_sites;

import pl.edu.agh.lab5_maze.Player;

public class BombedWall extends Wall {
    private boolean exploded = false;
    private final Room room1;
    private final Room room2;

    public BombedWall(Room room1, Room room2) {
        super('▒');
        this.room1 = room1;
        this.room2 = room2;
    }

    @Override
    public Room enter(Room previous, Player player) {
        if (!exploded) {
            if (player.getNumberOfSpecialItems() > 0) {
                player.useSpecialItem();
                representation = '◧';
                exploded = true;
            }
            return previous.enter(previous, player);
        } else {
            if (previous == room1) {
                return room2.enter(previous, player);
            } else if (previous == room2) {
                return room1.enter(previous, player);
            }
        }
        throw new IllegalArgumentException("Unable to enter this bombed wall from previous room");
    }

}
