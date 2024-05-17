package pl.edu.agh.lab5_maze;

import pl.edu.agh.lab5_maze.builder.MazeBuilder;
import pl.edu.agh.lab5_maze.utils.Vec2d;

public class MazeConstructionDirector {
    private String setupFilePath;

    public MazeConstructionDirector(String setupFilePath) {
        this.setupFilePath = setupFilePath;
    }

    public void make(MazeBuilder builder) {
        // TODO: read setup
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
    }
}
