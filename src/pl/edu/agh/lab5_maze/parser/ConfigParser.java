package pl.edu.agh.lab5_maze.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ConfigParser {
    private final String configFilePath;

    public ConfigParser(String configFilePath) {
        this.configFilePath = configFilePath;
    }

    public Config getConfig() throws IOException {
        Config config = new Config();

        BufferedReader reader = new BufferedReader(new FileReader(Objects.requireNonNull(this.getClass().getResource(configFilePath)).getPath()));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(" ");
            if (tokens.length < 1 || tokens[0].isEmpty()) {
                continue;
            }
            List<Integer> values = Arrays.stream(tokens).skip(1).map(Integer::parseInt).toList();

            ConfigElementType.valueOfIdentifier(tokens[0]).ifPresentOrElse(
                    configElementType -> config.addConfigElement(new ConfigElement(configElementType, values)),
                    () -> {
                        throw new IllegalArgumentException("Unable to parse token " + tokens[0]);
                    }
            );
        }
        reader.close();

        return config;
    }
}
