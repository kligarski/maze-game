package pl.edu.agh.lab5_maze.parser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Config {
    private final List<ConfigElement> configElements;

    public Config() {
        configElements = new LinkedList<>();
    }

    public void addConfigElement(ConfigElement configElement) {
        configElements.add(configElement);
    }

    public List<ConfigElement> getConfigElements() {
        return configElements;
    }

    public Optional<ConfigElement> getMazeFactoryConfig() {
        for (ConfigElement configElement : configElements) {

            if (configElement.getConfigElementType() == ConfigElementType.STANDARD ||
                    configElement.getConfigElementType() == ConfigElementType.ENCHANTED ||
                    configElement.getConfigElementType() == ConfigElementType.BOMBED) {
                return Optional.of(configElement);
            }
        }
        return Optional.empty();
    }

    public Optional<ConfigElement> getInitialConfig() {
        for (ConfigElement configElement : configElements) {

            if (configElement.getConfigElementType() == ConfigElementType.INITIAL) {
                return Optional.of(configElement);
            }
        }
        return Optional.empty();
    }

    public Optional<ConfigElement> getFinalConfig() {
        for (ConfigElement configElement : configElements) {

            if (configElement.getConfigElementType() == ConfigElementType.FINAL) {
                return Optional.of(configElement);
            }
        }
        return Optional.empty();
    }
}
