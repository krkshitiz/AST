import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RuleStorage {
    private static final String FILE_PATH = "data/rules.txt";

    public void saveRule(String rule) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(rule);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving rule: " + e.getMessage());
        }
    }

    public String[] loadRules() {
        List<String> rules = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                rules.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading rules: " + e.getMessage());
        }
        return rules.toArray(new String[0]);
    }
}
