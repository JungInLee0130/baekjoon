
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Vertex implements Comparable<Vertex> {
    private int num;
    private int weight;

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
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // E <= 2십만 -> 인접리스트
        ArrayList<ArrayList<Vertex>> graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) { // 정점
            graph.add(new ArrayList<>());
        }
        // 그래프 연결 : 끝점, 무게 모두 대입
        for (int i = 0; i < E; i++) {
            // 무방향 그래프
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Vertex(end, weight));
            graph.get(end).add(new Vertex(start, weight));
        }

        // 꼭 지나야할 시작점과 끝점
        st = new StringTokenizer(br.readLine());
        int mustNum1 = Integer.parseInt(st.nextToken());
        int mustNum2 = Integer.parseInt(st.nextToken());

        //Vertex mustVertex1 = new Vertex(mustNum1, 0);
        //Vertex mustVertex2 = new Vertex(mustNum2, 0);
        //disk[1] = 0; // 0이면 도달할수있는 경로가 없거나 그자체가 시작점.
        int sum1 = isPossible(graph, 1, mustNum1, mustNum2, N);
        int sum2 = isPossible(graph, 1, mustNum2, mustNum1, N);

        if (sum1 != -1 && sum2 != -1) { // 경로가 존재한다면
            int min = Math.min(sum1, sum2);
            bw.write(String.valueOf(min) + "\n"); // 작은거 리턴
        } else { // 경로가 없을때
            if (sum1 == -1 && sum2 == -1) { //둘다 없음
                bw.write(String.valueOf(-1) + "\n"); // -1리턴
            } else {
                int max = Math.max(sum1, sum2);
                bw.write(String.valueOf(max) + "\n"); // 작은거 리턴
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static int isPossible(ArrayList<ArrayList<Vertex>> graph, int start, int mustNum1, int mustNum2, int N) {
        int sum = 0;
        sum += dijkstra(graph, 1, mustNum1);
        sum += dijkstra(graph, mustNum1, mustNum2);
        sum += dijkstra(graph, mustNum2, N);
        if (sum >= INF) return -1;
        return sum;
    }


    private static int N;
    private static final int INF = 200_000_000;
    private static int dijkstra(ArrayList<ArrayList<Vertex>> graph, int start, int end) { // start : 1, end: N
        int[] disk = new int[N + 1]; // 1 ~ N
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(disk, INF);
        disk[start] = 0;

        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Vertex(start, disk[start])); // 1부터 시작 -> N에서 끝남.


        while (!priorityQueue.isEmpty()) {
            Vertex poll = priorityQueue.poll();
            int pollNum = poll.getNum();
            int pollWeight = poll.getWeight();

            if (visited[pollNum]) {
                continue;
            }
            visited[pollNum] = true;

            for (Vertex endVertex : graph.get(pollNum)) {
                int endVertexNum = endVertex.getNum();
                int edgeWeight = endVertex.getWeight();

                if (!visited[endVertexNum]
                        && (disk[endVertexNum] > edgeWeight + pollWeight)) { // disk에 있는 수가 더 크면
                    disk[endVertexNum] = edgeWeight + pollWeight; // 여태까지의 수와 edgeWeight를 더해줌.
                    priorityQueue.add(new Vertex(endVertexNum, disk[endVertexNum]));
                }
                // 갱신안되면 그냥 건너뜀.
                // 근데 특정 정점을 통과해야함.
            }
        }
        return disk[end]; // 도달할수가 없음
    }
}
