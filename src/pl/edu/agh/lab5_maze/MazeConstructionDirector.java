package pl.edu.agh.lab5_maze;

import pl.edu.agh.lab5_maze.builder.MazeBuilder;
import pl.edu.agh.lab5_maze.parser.Config;
import pl.edu.agh.lab5_maze.parser.ConfigElement;
import pl.edu.agh.lab5_maze.utils.Vec2d;

import java.util.LinkedList;
import java.util.List;

public class MazeConstructionDirector {
    private final Config config;

    public MazeConstructionDirector(Config config) {
        this.config = config;
    }

    private Vec2d createVec2dFromList(List<Integer> values) {
        Integer x = values.removeFirst();
        Integer y = values.removeFirst();

        return new Vec2d(x, y);
    }

    public void make(MazeBuilder builder) {
        for (ConfigElement configElement : config.getConfigElements()) {
            List<Integer> values = new LinkedList<>(configElement.getValues());
            switch (configElement.getConfigElementType()) {
                case ROOM -> builder.addRoom(createVec2dFromList(values));
                case SPECIAL_ROOM -> builder.addSpecialRoom(createVec2dFromList(values));

                case WALL -> {
                    Vec2d v1 = createVec2dFromList(values);
                    Vec2d v2 = createVec2dFromList(values);
                    builder.addWall(v1, v2);
                }
                case SPECIAL_WALL -> {
                    Vec2d v1 = createVec2dFromList(values);
                    Vec2d v2 = createVec2dFromList(values);
                    builder.addSpecialWall(v1, v2);
                }

                case DOOR -> {
                    Vec2d v1 = createVec2dFromList(values);
                    Vec2d v2 = createVec2dFromList(values);
                    builder.addDoor(v1, v2);
                }
                case SPECIAL_DOOR -> {
                    Vec2d v1 = createVec2dFromList(values);
                    Vec2d v2 = createVec2dFromList(values);
                    builder.addSpecialDoor(v1, v2);
                }

                case INITIAL -> builder.setInitialRoom(createVec2dFromList(values));
                case FINAL -> builder.setFinalRoom(createVec2dFromList(values));
            }
        }
    }
}

/*
//        Vec2d v1 = new Vec2d(0, 0);
//        Vec2d v2 = new Vec2d(0, 1);
//        Vec2d v3 = new Vec2d(1, 1);
//
//        builder.addRoom(v1);
//        builder.addSpecialRoom(v2);
//        builder.addRoom(v3);
//
//        builder.addDoor(v1, v2);
//        builder.addDoor(v2, v3);
//
//        builder.addWall(v1, new Vec2d(-1, 0));
//        builder.addWall(v1, new Vec2d(0, -1));
//        builder.addWall(v1, new Vec2d(1, 0));
//
//        builder.addWall(v2, new Vec2d(-1, 1));
//        builder.addWall(v2, new Vec2d(0, 2));
//
//        builder.addWall(v3, new Vec2d(1, 0));
//        builder.addWall(v3, new Vec2d(2, 1));
//        builder.addWall(v3, new Vec2d(1, 2));
//
//        builder.setInitialRoom(v1);
//        builder.setFinalRoom(v3);

        Vec2d v1 = new Vec2d(0, 0);
        Vec2d v2 = new Vec2d(0, 1);
        Vec2d v3 = new Vec2d(1, 1);
        Vec2d v4 = new Vec2d(2, 1);
        Vec2d v5 = new Vec2d(2, 2);

        builder.addRoom(v1);
        builder.addSpecialRoom(v2);
//        builder.addRoom(v2);
        builder.addRoom(v3);
        builder.addRoom(v4);
        builder.addRoom(v5);

        builder.addDoor(v1, v2);
        builder.addDoor(v2, v3);
//        builder.addDoor(v3, v4);
        builder.addSpecialWall(v3, v4);
        builder.addDoor(v4, v5);
//        builder.addSpecialWall(v4, v5);

        builder.addWall(v1, new Vec2d(-1, 0));
        builder.addWall(v1, new Vec2d(0, -1));
        builder.addWall(v1, new Vec2d(1, 0));

        builder.addWall(v2, new Vec2d(-1, 1));
        builder.addWall(v2, new Vec2d(0, 2));

        builder.addWall(v3, new Vec2d(1, 0));
        builder.addWall(v3, new Vec2d(1, 2));

        builder.addWall(v4, new Vec2d(2, 0));
        builder.addWall(v4, new Vec2d(3, 1));

        builder.addWall(v5, new Vec2d(2, 3));
        builder.addWall(v5, new Vec2d(3, 2));
        builder.addWall(v5, new Vec2d(1, 2));

        builder.setInitialRoom(v1);
        builder.setFinalRoom(v5);
 */