
import java.io.*;
import java.util.*;

// 우선, 음의 간선이 없다. 벨만 포드는 x
class Vertex {
    int num;
    int distance;

    public Vertex(int num, int distance) {
        this.num = num;
        this.distance = distance;
    }
}

public class Main {
    private static ArrayList<ArrayList<Vertex>> graph;
    private static int V;
    private static int E;

    private static int INF = 100_000_000; // V * c

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Vertex(end, distance));
        }
        // 그래프 연결 끝. 방향 그래프. 다중 간선 존재 x
        
        // 다익스트라 or 플로이드 워셜
        // 근데 두마을을 왕복? 다익스트라는 중복 방문을 하지않는다.
        // 플로이드로 풀어보자
        dist = new int[V + 1][V + 1]; // 1 ~ V
        Floyd();

        // 이제 i -> i 중 가장 적은것을 출력하면됨.
        int min = INF;
        for (int i = 1; i <= V; i++) {
            min = Math.min(dist[i][i], min);
        }

        if (min == INF) {
            bw.write(String.valueOf(-1));
        } else {
            bw.write(String.valueOf(min));
        }

        bw.flush();
        br.close();
        bw.close();
    }

    static int[][] dist;
    private static void Floyd() {
        // 초기화
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                dist[i][j] = INF;
            }
        }

        // 대입한대로 최단경로를 결정
        for (int i = 1; i <= V; i++) {
            for (Vertex endVertex: graph.get(i)) {
                int j = endVertex.num;
                int cost = endVertex.distance;
                dist[i][j] = Math.min(dist[i][j], cost);
            }
        }

        // 1부터 V까지 모두 거치는 경로와 대소관계 비교
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        // 계산 끝.
    }
}
