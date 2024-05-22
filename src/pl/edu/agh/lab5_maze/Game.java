package pl.edu.agh.lab5_maze;

import pl.edu.agh.lab5_maze.builder.CountingMazeBuilder;
import pl.edu.agh.lab5_maze.builder.StandardMazeBuilder;
import pl.edu.agh.lab5_maze.factory.BombedMazeFactory;
import pl.edu.agh.lab5_maze.factory.EnchantedMazeFactory;
import pl.edu.agh.lab5_maze.factory.MazeFactory;
import pl.edu.agh.lab5_maze.map_sites.MapSite;
import pl.edu.agh.lab5_maze.map_sites.Room;
import pl.edu.agh.lab5_maze.parser.Config;
import pl.edu.agh.lab5_maze.parser.ConfigElement;
import pl.edu.agh.lab5_maze.parser.ConfigElementType;
import pl.edu.agh.lab5_maze.parser.ConfigParser;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class Game extends JPanel implements Runnable {
    private final Player player;
    private final Maze maze;
    final int START_TILE_SIZE = 16;
    final int SCALE = 3;
    protected final int TILE_SIZE = START_TILE_SIZE*SCALE;
    protected final int MAX_SCREEN_COLUM = 16;
    protected final int MAX_SCREEN_ROW = 12;
    protected final int SCREEN_WIDTH = MAX_SCREEN_COLUM * TILE_SIZE;
    protected final int SCREEN_HEIGHT = MAX_SCREEN_ROW * TILE_SIZE;
    private Thread gameThread;
    private final KeyHandler keyH;
    private final TileManager tileManager;
    private final UI ui;
    private final Config config;

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public Game(String setupFilePath) throws IOException {
        ConfigParser configParser = new ConfigParser(setupFilePath);
        try {
            config = configParser.getConfig();
        } catch (IOException e) {
            throw new RuntimeException("Error parsing config", e);
        }

        MazeFactory mazeFactory = getMazeFactory();

        MazeConstructionDirector mazeConstructionDirector = new MazeConstructionDirector(config);
        player = new Player(this);
        tileManager = new TileManager(this, player);

        StandardMazeBuilder standardMazeBuilder = new StandardMazeBuilder(mazeFactory);
        mazeConstructionDirector.make(standardMazeBuilder);

        maze = standardMazeBuilder.getMaze();

        player.setCurrentRoom(maze.getInitialRoom().enter(maze.getInitialRoom(), player));

        CountingMazeBuilder countingMazeBuilder = new CountingMazeBuilder();
        mazeConstructionDirector.make(countingMazeBuilder);

        ui = new UI(this, player, countingMazeBuilder, mazeFactory);
        keyH = new KeyHandler();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    private MazeFactory getMazeFactory() {
        ConfigElement factoryConfig = config.getMazeFactoryConfig().orElseThrow();
        MazeFactory mazeFactory;
        switch (factoryConfig.getConfigElementType()) {
            case ConfigElementType.STANDARD -> mazeFactory = MazeFactory.getInstance();
            case ConfigElementType.ENCHANTED -> mazeFactory = EnchantedMazeFactory.getInstance();
            case ConfigElementType.BOMBED -> mazeFactory = BombedMazeFactory.getInstance();
            default -> throw new IllegalArgumentException("Invalid maze factory type");
        }
        return mazeFactory;
    }

    @Override
    public void run() {
        long currentTime;
        long lastRoomChangeTime = 0;
        long coolDownPeriod = 200000000; // 0.2second this is to avoid moving to the next room too quickly
        draw();
        while (gameThread != null) {
            currentTime = System.nanoTime();
            if (player.getCurrentRoom() == maze.getFinalRoom()) {
                System.out.println("*** You reached the final room, congratulations! ***");
                player.win();
                draw();
                return;
            }

            if (!player.isAlive()) {
                System.out.println("*** You died! ***");
                return;
            }

            Optional<MapSite> nextSite;
            nextSite = Optional.empty();

            if (currentTime - lastRoomChangeTime >= coolDownPeriod) {
                nextSite = move(nextSite);
            }

            if (nextSite.isPresent()) {
                MapSite mapSite = nextSite.get();
                try {
                    player.setCurrentRoom(mapSite.enter(player.getCurrentRoom(), player));
                    lastRoomChangeTime = currentTime;
                    draw();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            repaint();
        }
    }

    private Optional<MapSite> move(Optional<MapSite> nextSite){
        if (keyH.isUpPressed()) {
            nextSite = player.getCurrentRoom().getSide(Direction.NORTH);
            player.changeDirection(Direction.NORTH);
        } else if (keyH.isDownPressed()) {
            nextSite = player.getCurrentRoom().getSide(Direction.SOUTH);
            player.changeDirection(Direction.SOUTH);
        } else if (keyH.isRightPressed()) {
            nextSite = player.getCurrentRoom().getSide(Direction.EAST);
            player.changeDirection(Direction.EAST);
        } else if (keyH.isLeftPressed()) {
            nextSite = player.getCurrentRoom().getSide(Direction.WEST);
            player.changeDirection(Direction.WEST);
        }
        return nextSite;
    }

    private void draw() {
        Room room = player.getCurrentRoom();
        System.out.println("|=====|");
        System.out.println("|  " + room.getSide(Direction.NORTH).map(MapSite::getChar).orElse(' ') + "  |");
        System.out.println("| " + room.getSide(Direction.WEST).map(MapSite::getChar).orElse(' ')
                + room.getChar() + room.getSide(Direction.EAST).map(MapSite::getChar).orElse(' ') + " |");
        System.out.println("|  " + room.getSide(Direction.SOUTH).map(MapSite::getChar).orElse(' ') + "  |");
        System.out.println("|=====|");
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        ui.draw(g2);
        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose();
    }

}
