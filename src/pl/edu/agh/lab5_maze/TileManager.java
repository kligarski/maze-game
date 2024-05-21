package pl.edu.agh.lab5_maze;
import pl.edu.agh.lab5_maze.map_sites.MapSite;
import pl.edu.agh.lab5_maze.map_sites.Room;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class TileManager {
        private final Game gp;
        private Room room;
        private final Player player;
        private final int screenCenterX;
        private final int screenCenterY;
        private final BufferedImage cornerImage;

        public TileManager(Game gp, Player player) throws IOException {
            this.gp = gp;
            this.player = player;
            this.cornerImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("mapSite/corners.png")));
            screenCenterX = gp.SCREEN_WIDTH/2 - (gp.TILE_SIZE/2);
            screenCenterY = gp.SCREEN_HEIGHT/2 - (gp.TILE_SIZE/2);
        }

        public void changeRoom(Room room){
            this.room = room;
        }

        public void draw(Graphics2D g2){
            changeRoom(player.getCurrentRoom());
            Optional<MapSite> sides = room.getSide(Direction.NORTH);
            g2.drawImage(sides.get().getImage(), screenCenterX, screenCenterY - gp.TILE_SIZE, gp.TILE_SIZE, gp.TILE_SIZE, null);
            Optional<MapSite> sides1 = room.getSide(Direction.SOUTH);
            g2.drawImage(sides1.get().getImage(), screenCenterX, screenCenterY + gp.TILE_SIZE, gp.TILE_SIZE, gp.TILE_SIZE, null);
            Optional<MapSite> sides2 = room.getSide(Direction.EAST);
            g2.drawImage(sides2.get().getImage(), screenCenterX + gp.TILE_SIZE, screenCenterY, gp.TILE_SIZE, gp.TILE_SIZE, null);
            Optional<MapSite> sides3 = room.getSide(Direction.WEST);
            g2.drawImage(sides3.get().getImage(), screenCenterX - gp.TILE_SIZE, screenCenterY, gp.TILE_SIZE, gp.TILE_SIZE, null);
            g2.drawImage(room.getImage(), screenCenterX, screenCenterY, gp.TILE_SIZE, gp.TILE_SIZE, null);

            // empty corners
            g2.drawImage(cornerImage, screenCenterX - gp.TILE_SIZE, screenCenterY - gp.TILE_SIZE, gp.TILE_SIZE, gp.TILE_SIZE, null);
            g2.drawImage(cornerImage, screenCenterX + gp.TILE_SIZE, screenCenterY + gp.TILE_SIZE, gp.TILE_SIZE, gp.TILE_SIZE, null);
            g2.drawImage(cornerImage, screenCenterX - gp.TILE_SIZE, screenCenterY + gp.TILE_SIZE, gp.TILE_SIZE, gp.TILE_SIZE, null);
            g2.drawImage(cornerImage, screenCenterX + gp.TILE_SIZE, screenCenterY - gp.TILE_SIZE, gp.TILE_SIZE, gp.TILE_SIZE, null);

        }

    }

