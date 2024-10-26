import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RuleController {
    private final RuleService ruleService = new RuleService();
    private final RuleStorage ruleStorage = new RuleStorage();

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Rule Engine!");
        while (true) {
            System.out.println("1. Create a new rule");
            System.out.println("2. Load rules from database");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    createNewRule(scanner);
                    break;
                case 2:
                    loadRulesFromDatabase();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void createNewRule(Scanner scanner) {
        System.out.println("Enter your rule:");
        String ruleString = scanner.nextLine();
        Node ast = ruleService.createRule(ruleString);
        if (ast != null) {
            System.out.println("AST created successfully.");
            ruleStorage.saveRule(ruleString); // Save rule to database

            // Sample data for evaluation
            Map<String, Object> data = new HashMap<>();
            data.put("age", 35);
            data.put("department", "Sales");
            data.put("salary", 60000);
            data.put("experience", 3);

            boolean result = ruleService.evaluateRule(ast, data);
            System.out.println("Evaluation Result: " + result);
        }
    }

    private void loadRulesFromDatabase() {
        String[] rules = ruleStorage.loadRules();
        for (String rule : rules) {
            System.out.println("Evaluating rule: " + rule);
            Node ast = ruleService.createRule(rule);
            // Sample data for evaluation
            Map<String, Object> data = new HashMap<>();
            data.put("age", 35);
            data.put("department", "Sales");
            data.put("salary", 60000);
            data.put("experience", 3);

            boolean result = ruleService.evaluateRule(ast, data);
            System.out.println("Evaluation Result: " + result);
        }
    }
}
