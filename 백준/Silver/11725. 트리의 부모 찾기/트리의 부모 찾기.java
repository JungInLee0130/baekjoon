
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    private int num;
    private int parentNode;

    public Node(int num, int parentNode) {
        this.num = num;
        this.parentNode = parentNode;
    }

    public int getNum() {
        return num;
    }

    public int getParentNode() {
        return parentNode;
    }

}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // 자바는 Tree Library가 없다. 그냥 그래프로 풀어야함.
        // 인접리스트
        ArrayList<ArrayList<Integer>> graph= new ArrayList<>();
        graph.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 1은 root
            // 일단 무방향으로
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        // 1이 루트노드 : start
        BFS(graph, N);

        for (int i = 2; i <= N; i++) {
            bw.write(String.valueOf(parentNode[i]) + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static int[] parentNode; // 부모노드 저장

    private static void BFS(ArrayList<ArrayList<Integer>> graph, int N) {
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1]; // 방문 중복 방지
        Node root = new Node(1,-1); // 처음은 루트라 부모노드가 없음
        queue.add(root);
        parentNode = new int[N + 1];

        while (!queue.isEmpty()) {
            Node poll = queue.poll();

            if (visited[poll.getNum()]) {
                continue;
            }
            visited[poll.getNum()] = true;

            // ! - 이진노드가 아님..... -> left, right node 삭제
            for (Integer endVertex : graph.get(poll.getNum())) {
                // node중 한 공간이 비어있으면
                if (poll.getParentNode() != endVertex) {
                    // 부모노드 저장배열에 부모노드 저장
                    parentNode[endVertex] = poll.getNum();
                    // 노드를 채울때 현재 노드를 루트로 넣어준다.
                    // 생각해보니 sibling 노드를 신경쓸필요가 없다.
                    queue.add(new Node(endVertex, poll.getNum()));
                }
            }
        }
    }
}
