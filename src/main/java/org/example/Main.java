package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static void main() throws IOException {
        String jsonContent = Files.readString(Path.of("data/map.json"));
        ObjectMapper objectMapper = new ObjectMapper();

        addArrayObject(objectMapper, jsonContent);
    }

    private static void addArrayObject(ObjectMapper objectMapper, String jsonContent) throws IOException {
        ObjectNode rootNode = (ObjectNode) objectMapper.readTree(jsonContent);
        ArrayNode newArray = objectMapper.createArrayNode();

        ObjectNode firstItem = objectMapper.createObjectNode();
        firstItem.put("key1", "value1");
        firstItem.put("key2", "value2");
        newArray.add(firstItem);

        ObjectNode secondItem = objectMapper.createObjectNode();
        secondItem.put("key1", "value1");
        secondItem.put("key2", "value2");
        newArray.add(secondItem);

        rootNode.set("array", newArray);

        objectMapper.writeValue(new File("data/new_json.json"), rootNode);
    }

    private static void editMap(ObjectMapper objectMapper, String jsonContent) throws IOException {
        ObjectNode mapData = (ObjectNode) objectMapper.readTree(jsonContent);
        mapData.put("city", "Kyiv");

        ArrayNode linesArray = (ArrayNode) mapData.get("lines");
        for (JsonNode line : linesArray) {
            ObjectNode lineData = (ObjectNode) line;
            lineData.remove("color");
        }
        objectMapper.writeValue(new File("data/modifield_map.json"), mapData);
    }

    private static void saveMyLines(ObjectMapper objectMapper) throws IOException {
        List<MetroLine> metroLines = new ArrayList<>(List.of(
                new MetroLine(1, "first", "black"),
                new MetroLine(2, "second", "white"),
                new MetroLine(3, "thirty", "orange")
        ));
        objectMapper.writeValue(new File("data/my_lines.json"), metroLines);

        List<MetroLine> readMetroLines = objectMapper.readValue(
                new File("data/my_lines.json"),
                new TypeReference<>() {
                });
        readMetroLines.forEach(System.out::println);
    }

    private static void readConnectionToObject(ObjectMapper objectMapper) throws IOException {
        String jsonData = Files.readString(Path.of("data/map.json"));
        JsonNode rootNode = objectMapper.readTree(jsonData);

        List<Connection> connections = new ArrayList<>();
        JsonNode connectionsNode = rootNode.get("connections");
        for (JsonNode group : connectionsNode) {
            for (JsonNode station : group) {
                connections.add(objectMapper.treeToValue(station, Connection.class));
            }
        }
        connections.forEach(System.out::println);
    }

    private static String writeJson(ObjectMapper objectMapper) throws JsonProcessingException {
        Station station = new Station("TEST STATION", 1);
        String json = objectMapper.writeValueAsString(station);
        System.out.println(json);
        return json;
    }

    private static void readJson(ObjectMapper objectMapper, String json) throws JsonProcessingException {
        Station parsedStation = objectMapper.readValue(json, Station.class);
        System.out.println(parsedStation);
    }

    private static void readStation(ObjectMapper objectMapper) throws IOException {
        String stationJson = Files.readString(Path.of("data/station.json"));
        readJson(objectMapper, stationJson);
    }

    private static void readJsonLines(ObjectMapper objectMapper, String jsonContent) throws JsonProcessingException {
        JsonNode rootNode = objectMapper.readTree(jsonContent);
        JsonNode linesNode = rootNode.get("lines");

        List<MetroLine> metroLines = new ArrayList<MetroLine>();
        for (JsonNode lineNode : linesNode) {
            metroLines.add(objectMapper.treeToValue(lineNode, MetroLine.class));
        }
        metroLines.forEach(System.out::println);
    }

    private static JsonNode getConnections(JsonNode jsonData, ObjectMapper objectMapper) {
        JsonNode connectionsNode = jsonData.get("connections");
        Map<String, Integer> stationConnections = new HashMap<>();
        for (JsonNode connectionGroup : connectionsNode) {
            for (JsonNode station : connectionGroup) {
                String stationName = station.get("station").asText();
                stationConnections.put(stationName, stationConnections.getOrDefault(stationName, 0) + 1);
            }
        }
        ArrayNode resultArray = objectMapper.createArrayNode();
        for (Map.Entry<String, Integer> connectionEntry : stationConnections.entrySet()) {
            ObjectNode stationNode = objectMapper.createObjectNode();
            stationNode.put("name", connectionEntry.getKey());
            stationNode.put("countConnection", connectionEntry.getValue());
            resultArray.add(stationNode);
        }
        return resultArray;
    }

    private static JsonNode getLines(JsonNode jsonData) {
        JsonNode stations = jsonData.get("stations");
        JsonNode lines = jsonData.get("lines");

        for (JsonNode line : lines) {
            ObjectNode lineData = (ObjectNode) line;

            JsonNode lineNumber = line.get("number");
            JsonNode lineStations = stations.get(String.valueOf(lineNumber.asInt()));

            int stationCount = lineStations.size();
            lineData.remove("color");
            lineData.put("stationsCount", stationCount);
        }
        return lines;
    }
}
