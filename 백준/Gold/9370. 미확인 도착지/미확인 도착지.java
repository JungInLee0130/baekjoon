import java.io.*;
import java.util.*;

class Vertex implements Comparable<Vertex> {
    int num;
    int weight;

    public Vertex(int num, int weight) {
        this.num = num;
        this.weight = weight;
    }

    public int getNum() {
        return num;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Vertex o) {
        return this.weight - o.weight;
    }
}

public class Main {
    private static final int INF = 50_000_000;
    private static int mustVertex1;
    private static int mustVertex2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            // 교차로(정점), 도로(간선), 목적지 후보
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            // 출발점, 반드시 지나야할 두 정점
            int firstVertex = Integer.parseInt(st.nextToken());
            mustVertex1 = Integer.parseInt(st.nextToken());
            mustVertex2 = Integer.parseInt(st.nextToken());

            // 인접 리스트 : 그래프 그리기
            ArrayList<ArrayList<Vertex>> graph = new ArrayList<>();
            graph.add(new ArrayList<>());
            for (int i = 1; i <= V; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken()) * 2;

                if (start == mustVertex1 && end == mustVertex2
                        || start == mustVertex2 && end == mustVertex1) {
                    weight -= 1;
                }

                // 무방향 그래프(양방향)
                graph.get(start).add(new Vertex(end, weight));
                graph.get(end).add(new Vertex(start, weight));
            }

            // 목적지 후보 배열 x
            int[] x = new int[t];
            for (int i = 0; i < t; i++) {
                x[i] = (Integer.parseInt(br.readLine()));
            }

            // 일반 최단경로
            int[] minway = new int[V + 1];
            BFS(graph, firstVertex, V, minway);



            // 오름차순 정렬후 출력
            Arrays.sort(x);

            // 최단 경로 비교
            for (int i = 0; i < t; i++) {
                if (minway[x[i]] % 2 != 0) {
                    bw.write(String.valueOf(x[i]) + " ");
                }
            }
            bw.write("\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static void BFS(ArrayList<ArrayList<Vertex>> graph, int firstVertex, int V, int[] minway) {
        // 초기조건 : 우선순위큐 선언(실행시간 감소), visited배열 선언(중복 방문 제거)
        // , minway INF로 초기화, 초기방문 노드는 최단경로가 0으로
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        boolean[] visited = new boolean[V + 1]; // 1 ~ V // 중복제거
        Arrays.fill(minway, INF);
        minway[firstVertex] = 0;

        priorityQueue.add(new Vertex(firstVertex, 0));
        while (!priorityQueue.isEmpty()) {
            Vertex poll = priorityQueue.poll();
            int pollNum = poll.getNum();
            int pollWeight = poll.getWeight();

            // 방문한거면 실행 x
            if (visited[pollNum]) {
                continue;
            }
            visited[pollNum] = true;

            // 끝점 방문하면서 최단경로 구하기
            for (Vertex endVertex :graph.get(pollNum)) {
                int endVertexNum = endVertex.getNum();
                int endVertexWeight = endVertex.getWeight();

                // 미방문, 최단경로가 크면 -> 바꿈
                if (!visited[endVertexNum]
                        && pollWeight + endVertexWeight < minway[endVertexNum]) {
                    minway[endVertexNum] = pollWeight + endVertexWeight;
                    priorityQueue.add(new Vertex(endVertexNum, minway[endVertexNum]));
                }
            }
        }
    }

}

