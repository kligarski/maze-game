package pl.edu.agh.lab5_maze.map_sites;

import pl.edu.agh.lab5_maze.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class EnchantedRoom extends Room {
    private boolean visited = false;

    public EnchantedRoom(char representation, BufferedImage image, String hint){
        super(representation,image, hint);
    }

    @Override
    public Room enter(Room previous, Player player) {
        if (!visited) {
            visited = true;
        } else {
            player.kill();
        }
        return super.enter(previous, player);
    }
}
