import java.io.*;
import java.util.*;

class Vertex {
    int num;
    int distance;

    // 깊이가 최대인걸 리턴

    public Vertex(int num, int distance) {
        this.num = num;
        this.distance = distance;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int V = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Vertex>> graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        for (int i = 1; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());

            while (true) {
                int end = Integer.parseInt(st.nextToken());
                if (end == -1) {
                    break;
                }
                int weight = Integer.parseInt(st.nextToken());

                // 입력 자체가 양방향
                graph.get(start).add(new Vertex(end, weight));
            }
        }

        // DFS로 전환
        visited = new boolean[V + 1]; // 1 ~ V
        // 1. endVertex 잘 출력
        DFS(graph, 1, 0);
        // 2. 최대 거리 출력
        Arrays.fill(visited, false);
        DFS(graph, endVertex, 0);

        bw.write(String.valueOf(maxDistance));

        bw.flush();
        br.close();
        bw.close();
    }

    private static boolean[] visited;
    private static int endVertex;

    private static int maxDistance = 0;
    // depth를 distance대신 사용한다면?
    private static void DFS(ArrayList<ArrayList<Vertex>> graph, int start, int distance) {
        if (distance > maxDistance) {
            maxDistance = distance;
            endVertex = start; // 끝점 갱신
        }
        visited[start] = true;

        for (int j = 0; j < graph.get(start).size() ; j++) { // start의 end 정점들을 뒤짐.
            Vertex vertex = graph.get(start).get(j); // 끝점
            if (!visited[vertex.num]) { // 방문하지않았으면
                visited[vertex.num] = true;
                DFS(graph, vertex.num, vertex.distance + distance);
            }
        }
    }


}
