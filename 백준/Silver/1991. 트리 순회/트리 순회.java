
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Tree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    // 트리에 값을 추가
    public void createNode(char data, char leftData, char rightData) {
        if (root == null) { // root 노드 채움
            root = new Node(data);

            if (root.leftNode == null) {
                root.leftNode = new Node(leftData);
            }
            if (root.rightNode == null) {
                root.rightNode = new Node(rightData);
            }
            root.parentNode = new Node('.');
        } else { // 루트 노드 안빔.
            searchNode(root, data, leftData, rightData);
        }
    }

    // 값을 어느위치에 추가할지 수색
    public void searchNode(Node node, char data, char leftData, char rightData) {
        if (node == null) {
            return;
        }
        else if (node.num == data) { // 같으면 멈추고 left, right중에 하나에 대임
            if (node.leftNode == null) {
                node.leftNode = new Node(leftData);
            }
            if (node.rightNode == null) {
                node.rightNode = new Node(rightData);
            }
                node.parentNode = new Node(data); // 부모 노드 연결
        } else { // 같지 않으면 계속 탐색
            searchNode(node.leftNode, data, leftData, rightData);
            searchNode(node.rightNode, data, leftData, rightData);
        }
    }

    // 전위,중위,후위 순회 메소드
    public void preorder(Node node) {
        if (node != null) {
            if (node.num != '.') {
                System.out.print(node.num);
            }
            if (node.leftNode != null) {
                preorder(node.leftNode);
            }
            if (node.rightNode != null) {
                preorder(node.rightNode);
            }
        }
    }
    public void inorder(Node node) {
        if (node != null) {
            if (node.leftNode != null) {
                inorder(node.leftNode);
            }
            if (node.num != '.') {
                System.out.print(node.num);
            }
            if (node.rightNode != null) {
                inorder(node.rightNode);
            }
        }
    }

    public void postorder(Node node) {
        if (node != null) {
            if (node.leftNode != null) {
                postorder(node.leftNode);
            }
            if (node.rightNode != null) {
                postorder(node.rightNode);
            }
            if (node.num != '.') {
                System.out.print(node.num);
            }
        }
    }
}
class Node {
    char num;
    Node parentNode;
    Node leftNode;
    Node rightNode;

    public Node(char num) {
        this.num = num;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Tree tree = new Tree(); // 트리선언
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            char num = st.nextToken().charAt(0);
            char leftChild = st.nextToken().charAt(0);
            char rightChild = st.nextToken().charAt(0);

            tree.createNode(num, leftChild, rightChild);
        }

        tree.preorder(tree.getRoot());
        System.out.println();
        tree.inorder(tree.getRoot());
        System.out.println();
        tree.postorder(tree.getRoot());
        ;
        bw.flush();
        br.close();
        bw.close();
    }



}
