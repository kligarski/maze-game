package pl.edu.agh.lab5_maze.utils;

import pl.edu.agh.lab5_maze.Direction;

import java.util.Objects;
import java.util.Optional;

public class Vec2d {
    private final int x;
    private final int y;

    public Vec2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private Vec2d subtract(Vec2d other) {
        return new Vec2d(this.x - other.x, this.y  - other.y);
    }

    public static Optional<Direction> getDirection(Vec2d v1, Vec2d v2) {
        Vec2d difference = v1.subtract(v2);

        if (difference.x == -1 && difference.y == 0) {
            return Optional.of(Direction.SOUTH);
        } else if (difference.x == 0 && difference.y == 1) {
            return Optional.of(Direction.WEST);
        } else if (difference.x == 1 && difference.y == 0) {
            return Optional.of(Direction.NORTH);
        } else if (difference.x == 0 && difference.y == -1) {
            return Optional.of(Direction.EAST);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vec2d vec2d = (Vec2d) o;
        return x == vec2d.x && y == vec2d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
