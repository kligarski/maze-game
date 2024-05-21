package pl.edu.agh.lab5_maze.map_sites;
import pl.edu.agh.lab5_maze.Player;
import java.awt.image.BufferedImage;
public class Wall implements MapSite {
    protected char representation;
    protected BufferedImage image;

    public Wall(char representation, BufferedImage image) {
        this.representation = representation;
        this.image = image;
    }

    @Override
    public Room enter(Room previous, Player player){
        return previous.enter(previous, player);
    }
    @Override
    public char getChar() {
        return representation;
    }
    @Override
    public BufferedImage getImage(){
        return image;
    }

}
