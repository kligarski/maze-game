package pl.edu.agh.lab5_maze;

import pl.edu.agh.lab5_maze.map_sites.Room;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player {
    private BufferedImage up, down, left, right;
    private Direction direction =  Direction.NORTH;
    private final Game gp;
    private Room currentRoom;
    private boolean alive = true;
    private boolean won = false;
    private int numberOfSpecialItems = 0;

    public Player(Game gp){
        getPlayerImage();
        this.gp = gp;
    }

    public void getPlayerImage(){
        try {
            up = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/7.png")));
            down = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/1.png")));
            left = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/3.png")));
            right = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/4.png")));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = switch (direction) {
            case NORTH -> up;
            case SOUTH -> down;
            case EAST -> right;
            case WEST -> left;
        };
        g2.drawImage(image, gp.SCREEN_WIDTH/2 - (gp.TILE_SIZE/2), gp.SCREEN_HEIGHT/2- (gp.TILE_SIZE/2), gp.TILE_SIZE, gp.TILE_SIZE, null);
    }
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public boolean isAlive() {
        return alive;
    }

    public void win(){ this.won = true;}

    public boolean isWon() {
        return won;
    }

    public void kill() {
        alive = false;
    }

    public void addSpecialItem() {
        ++numberOfSpecialItems;
    }

    public void useSpecialItem() {
        --numberOfSpecialItems;
    }

    public int getNumberOfSpecialItems() {
        return numberOfSpecialItems;
    }

    public void changeDirection(Direction direction){
        this.direction = direction;
    }


}
