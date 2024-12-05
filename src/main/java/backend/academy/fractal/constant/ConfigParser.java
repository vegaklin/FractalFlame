package backend.academy.fractal.constant;

import backend.academy.fractal.transformation.TransformationType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ConfigParser {
    private final Map<String, String> properties = new HashMap<>();
    private final Map<String, List<String>> listProperties = new HashMap<>();

    public ConfigParser(String filePath) {
        loadConfig(filePath);
    }

    private void loadConfig(String filePath) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new IllegalArgumentException("Configuration file not found: " + filePath);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            String currentKey = null;
//            StringBuilder currentListValues = new StringBuilder();
            List<String> currentListValues = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.endsWith(":")) {
                    currentKey = line.substring(0, line.length() - 1).trim();
                } else if (line.startsWith("- ")) {
                    currentListValues.add(line.substring(2).trim());
                } else {
                    if (currentKey != null && !currentListValues.isEmpty()) {
                        listProperties.put(currentKey, List.copyOf(currentListValues));
                        currentKey = null;
                        currentListValues.clear();
                    }
                    int colonIndex = line.indexOf(':');
                    if (colonIndex != -1) {
                        String key = line.substring(0, colonIndex).trim();
                        String value = line.substring(colonIndex + 1).trim();
                        if (value.startsWith("\"") && value.endsWith("\"") && value.length() > 1) {
                            value = value.substring(1, value.length() - 1);
                        }
                        properties.put(key, value);
                    }
                }
            }
            if (!currentListValues.isEmpty()) {
                listProperties.put(currentKey, List.copyOf(currentListValues));
            }

        } catch (IOException e) {
            log.error("Error reading configuration file: ", e);
            throw new RuntimeException("Failed to load configuration file: ", e);
        }
    }

    public <T> T get(String key, Class<T> type) {
        String value = properties.get(key);
        if (value == null) {
            throw new IllegalArgumentException("Key not found: " + key);
        }

        return switch (type.getSimpleName()) {
            case "String" -> type.cast(value);
            case "Integer" -> type.cast(Integer.valueOf(value));
            case "Boolean" -> type.cast(Boolean.valueOf(value));
            case "Double" -> type.cast(Double.valueOf(value));
            default -> throw new IllegalArgumentException("Unsupported type: " + type);
        };
    }

    public List<TransformationType> getTransformationTypes(String key) {
        List<String> valueList = listProperties.get(key);
        if (valueList != null) {
            return valueList.stream()
                .map(TransformationType::valueOf)
                .collect(Collectors.toList());
        }
        return List.of();
    }
}
