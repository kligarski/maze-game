package pl.edu.agh.lab5_maze;

import pl.edu.agh.lab5_maze.builder.CountingMazeBuilder;
import pl.edu.agh.lab5_maze.factory.BombedMazeFactory;
import pl.edu.agh.lab5_maze.factory.EnchantedMazeFactory;
import pl.edu.agh.lab5_maze.factory.MazeFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class UI {
    private final Game gp;
    private final Font arial_40;
    private final CountingMazeBuilder countingMazeBuilder;
    private final Player player;
    private final MazeFactory mazeFactory;
    private BufferedImage wallImage;
    private BufferedImage eWallImage;
    private BufferedImage doorImage;
    private BufferedImage bombImage;
    public UI(Game gp, Player player, CountingMazeBuilder countingMazeBuilder, MazeFactory mazeFactory){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN,30);
        this.player = player;
        this.countingMazeBuilder = countingMazeBuilder;
        this.mazeFactory = mazeFactory;
        uploadTextures();
    }

    protected void uploadTextures(){
        try {
            this.wallImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("mapSite/wall.png")));
            this.eWallImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("mapSite/eWall.png")));
            this.doorImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("mapSite/door.png")));
            this.bombImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("mapSite/bomb.png")));
        } catch(IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void draw(Graphics2D g2){
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawImage(player.getCurrentRoom().getImage(), 10, 10, gp.TILE_SIZE, gp.TILE_SIZE, null);
        g2.drawString(countingMazeBuilder.getRoomsNumber(), 74 ,48);
        if(mazeFactory instanceof EnchantedMazeFactory){
            g2.drawImage(eWallImage, 10, 60, gp.TILE_SIZE, gp.TILE_SIZE, null);
        } else {
            g2.drawImage(wallImage, 10, 60, gp.TILE_SIZE, gp.TILE_SIZE, null);
        }
        g2.drawString(countingMazeBuilder.getWallsNumber(), 74 ,95);
        g2.drawImage(doorImage, 10, 110, gp.TILE_SIZE, gp.TILE_SIZE, null);
        g2.drawString(countingMazeBuilder.getDoorsNumber(), 74 ,145);
        if(mazeFactory instanceof BombedMazeFactory){
            g2.drawImage(bombImage, 14*gp.TILE_SIZE, 10, gp.TILE_SIZE, gp.TILE_SIZE, null);
            g2.drawString(STR."\{player.getNumberOfSpecialItems()}", 15*gp.TILE_SIZE + 15 ,48);
        }

        if(player.isAlive() && !player.isWon()) {
            g2.drawString(player.getCurrentRoom().getHint(), 10, 10 * gp.TILE_SIZE);
        } else if(!player.isAlive()) {
            g2.drawString("*** You died! ***", 10, 10 * gp.TILE_SIZE);
        }

        if(player.isWon()){
            g2.drawString("*** You reached the final room, congratulations! ***", 10, 10 * gp.TILE_SIZE);
        }
    }
}
