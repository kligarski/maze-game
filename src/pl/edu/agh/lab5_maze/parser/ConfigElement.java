package pl.edu.agh.lab5_maze.parser;

import java.util.List;

public class ConfigElement {
    private final ConfigElementType configElementType;
    private final List<Integer> values;

    public ConfigElement(ConfigElementType configElementType, List<Integer> values) {
        this.configElementType = configElementType;
        this.values = values;
    }

    public ConfigElementType getConfigElementType() {
        return configElementType;
    }

    public List<Integer> getValues() {
        return values;
    }
}
