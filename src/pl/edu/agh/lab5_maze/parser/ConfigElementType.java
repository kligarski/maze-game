package pl.edu.agh.lab5_maze.parser;

import java.util.Optional;

public enum ConfigElementType {
    ROOM("Room", 2),
    SPECIAL_ROOM("SpecialRoom", 2),
    WALL("Wall", 4),
    SPECIAL_WALL("SpecialWall", 4),
    DOOR("Door", 4),
    SPECIAL_DOOR("SpecialDoor", 4),

    STANDARD("Standard", 0),
    ENCHANTED("Enchanted", 0),
    BOMBED("Bombed", 0),

    INITIAL("Initial", 2),
    FINAL("Final", 2);

    public final String configIdentifier;
    public final int numberOfParameters;

    public static Optional<ConfigElementType> valueOfIdentifier(String identifier) {
        for (ConfigElementType e : values()) {
            if (e.configIdentifier.equals(identifier)) {
                return Optional.of(e);
            }
        }
        return Optional.empty();
    }

    ConfigElementType(String configIdentifier, int numberOfParameters) {
        this.configIdentifier = configIdentifier;
        this.numberOfParameters = numberOfParameters;
    }


}
