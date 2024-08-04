package org.algorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WizardJsonParser extends BaseAlgorithm<AlgorithmCustomData> {
    public WizardJsonParser() {
        super(new AlgorithmCustomData());
    }

    @Override
    public void execute() {
        Path path = Paths.get(Path.of("").toAbsolutePath().toString(), "resources", "input", "users.json");
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toString()))) {
            String line;

            StringBuilder jsonBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            processJson(jsonBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processJson(String json) {
        System.out.println(json);
    }

    @Override
    protected String describe() {
        return "Parsing wizard ";
    }
}
