package pl.edu.agh.lab5_maze.map_sites;
import pl.edu.agh.lab5_maze.Player;
import java.awt.image.BufferedImage;

public class BombedRoom extends Room {
    private boolean bombPickedUp = false;

    public BombedRoom(char representation, BufferedImage image, String hint){
        super(representation, image, hint);
    }

    @Override
    public Room enter(Room previous, Player player) {
        if (!bombPickedUp) {
            player.addSpecialItem();
            bombPickedUp = true;
        }
        return super.enter(previous, player);
    }
}
