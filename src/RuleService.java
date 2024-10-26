import java.util.HashMap;
import java.util.Map;

public class RuleService {

    public Node createRule(String ruleString) {
        // Simple parser for rules
        try {
            if (ruleString.contains("AND")) {
                String[] parts = ruleString.split("AND");
                Node left = createOperandNode(parts[0].trim());
                Node right = createOperandNode(parts[1].trim());
                return new Node("operator", left, right, "AND");
            } else if (ruleString.contains("OR")) {
                String[] parts = ruleString.split("OR");
                Node left = createOperandNode(parts[0].trim());
                Node right = createOperandNode(parts[1].trim());
                return new Node("operator", left, right, "OR");
            } else {
                return createOperandNode(ruleString);
            }
        } catch (Exception e) {
            System.out.println("Error parsing rule: " + e.getMessage());
            return null;
        }
    }

    private Node createOperandNode(String operand) {
        String[] parts = operand.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid operand format: " + operand);
        }
        String attribute = parts[0];
        String operator = parts[1];
        String value = parts[2].replace("'", ""); // Remove single quotes

        return new Node("operand", null, null, new Condition(attribute, operator, value));
    }

    public boolean evaluateRule(Node ast, Map<String, Object> data) {
        if (ast == null) return false; // Handle null AST
        if (ast.type.equals("operand")) {
            Condition condition = (Condition) ast.value;
            Object attributeValue = data.get(condition.attribute);
            return evaluateCondition(attributeValue, condition.operator, condition.value);
        } else if (ast.type.equals("operator")) {
            boolean leftResult = evaluateRule(ast.left, data);
            boolean rightResult = evaluateRule(ast.right, data);
            return ast.value.equals("AND") ? leftResult && rightResult : leftResult || rightResult;
        }
        return false;
    }

    private boolean evaluateCondition(Object attributeValue, String operator, String value) {
        if (attributeValue == null) return false; // Handle null values
        switch (operator) {
            case ">":
                return (Integer) attributeValue > Integer.parseInt(value);
            case "<":
                return (Integer) attributeValue < Integer.parseInt(value);
            case "=":
                return attributeValue.toString().equals(value);
            default:
                return false;
        }
    }

    private static class Condition {
        String attribute;
        String operator;
        String value;

        public Condition(String attribute, String operator, String value) {
            this.attribute = attribute;
            this.operator = operator;
            this.value = value;
        }
    }
}
