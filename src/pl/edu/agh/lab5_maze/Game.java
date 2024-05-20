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

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class Game {
    private final Player player;
    private final Maze maze;
    private final Config config;

    public Game(String setupFilePath) {
        ConfigParser configParser = new ConfigParser(setupFilePath);
        try {
            config = configParser.getConfig();
        } catch (IOException e) {
            throw new RuntimeException("Error parsing config", e);
        }

        MazeFactory mazeFactory = getMazeFactory();

        MazeConstructionDirector mazeConstructionDirector = new MazeConstructionDirector(config);
        player = new Player();

        StandardMazeBuilder standardMazeBuilder = new StandardMazeBuilder(mazeFactory);
        mazeConstructionDirector.make(standardMazeBuilder);

        maze = standardMazeBuilder.getMaze();

        player.setCurrentRoom(maze.getInitialRoom().enter(maze.getInitialRoom(), player));

        CountingMazeBuilder countingMazeBuilder = new CountingMazeBuilder();
        mazeConstructionDirector.make(countingMazeBuilder);

        System.out.println(countingMazeBuilder.getCounts());
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

    public void run() {
        Scanner in = new Scanner(System.in);
        while (true) {
            draw();

            if (player.getCurrentRoom() == maze.getFinalRoom()) {
                System.out.println("*** You reached the final room, congratulations! ***");
                return;
            }

            if (!player.isAlive()) {
                System.out.println("*** You died! ***");
                return;
            }

            char c = in.next().toLowerCase().charAt(0);

            Optional<MapSite> nextSite = switch (c) {
                case 'w' -> player.getCurrentRoom().getSide(Direction.NORTH);
                case 'd' -> player.getCurrentRoom().getSide(Direction.EAST);
                case 's' -> player.getCurrentRoom().getSide(Direction.SOUTH);
                case 'a' -> player.getCurrentRoom().getSide(Direction.WEST);
                default -> Optional.empty();
            };

            nextSite.ifPresent(mapSite -> player.setCurrentRoom(mapSite.enter(player.getCurrentRoom(), player)));
        }
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

}
