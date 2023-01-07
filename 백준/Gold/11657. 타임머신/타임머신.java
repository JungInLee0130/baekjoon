import java.io.*;
import java.util.*;

class Edge{
    private int start;
    private int end;
    private int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());


        // 그래프 그리기
        ArrayList<Edge> graph = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.add(new Edge(start, end, weight));
        }

        // 음수 간선 순환 : -1 break, 경로가 없으면 -1
        // 나머지 출력

        // 음수간선의 최단경로 구하기.
        long[] minway = new long[V + 1];
        Arrays.fill(minway, INF);
        boolean isNegativeCycle = BelmanFord(graph, minway, V, E);

        for (int i = 2; i <= V; i++) {
            if (isNegativeCycle) {
                bw.write(String.valueOf(-1) + "\n");
                break;
            } else if (minway[i] == INF) {
                bw.write(String.valueOf(-1) + "\n");
            } else {
                bw.write(String.valueOf(minway[i]) + "\n");
            }
        }


        bw.flush();
        br.close();
        bw.close();
    }

    private static int INF = 60_000_000;
    private static boolean BelmanFord(ArrayList<Edge> graph, long[] minway, int V, int E) {
        // visited는 없어도됨. 어짜피 모두 거쳐야함.
        // O(VE) : priorityQueue 없어도됨.
        // minway 초기화
        minway[1] = 0;

        for (int i = 1; i <= V; i++) {
            for (int j = 0; j < E; j++) {
                Edge edge = graph.get(j);
                int edgeStart = edge.getStart();
                int edgeEnd = edge.getEnd();
                int edgeWeight = edge.getWeight();

                // INF와 더했을때 INF-1이 되면 걸러지지않는다
                if (minway[edgeStart] != INF && (edgeWeight + minway[edgeStart] < minway[edgeEnd])) {
                    minway[edgeEnd] = minway[edgeStart] + edgeWeight;
                    if (i == V) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}

