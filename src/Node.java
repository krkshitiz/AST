public class Node {
    String type; // "operator" or "operand"
    Node left;
    Node right;
    Object value; // Value for operand nodes

    public Node(String type, Node left, Node right, Object value) {
        this.type = type;
        this.left = left;
        this.right = right;
        this.value = value;
    }
}