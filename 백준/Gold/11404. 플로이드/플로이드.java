import java.io.*;
import java.util.*;

class Vertex{
    int num;
    int cost;

    public Vertex(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }
}
public class Main {
    private static ArrayList<ArrayList<Vertex>> graph;
    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Vertex(end, cost));
            //graph.get(end).add(new Vertex(start, cost));
        }

        // 그래프 연결끝, 정점, 간선 모두 입력받음.

        dist = new int[N + 1][N + 1]; // 1 ~ N까지 최단경로를 저장
        floydWarshall();

        // 최단경로 dist[][] 배열 출력
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (dist[i][j] == INF) {
                    dist[i][j] = 0;
                }
                bw.write(String.valueOf(dist[i][j]) + " ");
            }
            bw.write("\n");
        }



        bw.flush();
        br.close();
        bw.close();
    }

    static int[][] dist;
    static int INF = 100_000_000; // n : 100 * cost : 100_000 _는 자릿수 구별
    private static void floydWarshall() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    dist[i][j] = 0; // 자기 자신으로의 경로는 최단경로가 없음. 0.
                    continue;
                }
                dist[i][j] = INF; // 최단경로 배열 초기화
            }
        }

        // 먼가 이상함. 일단 후보 1이 여기. 최단경로가 더해지지않은 느낌.
        // 해결법 : 입력받은 간선들의 최단경로를 미리 입력해둠.
        // i -> j 경로중 최단경로를 dist에 미리 저장
        // 현재 고민: arraylist는 가독성이 딸림... 약간의 생각을 거쳐야함. 어떻게 해결할까?
        for (int i = 1; i <= N; i++) {
            for (Vertex endVertex: graph.get(i)) { // 끝점까지의 거리중 최단경로를 dist 배열에 대입
                dist[i][endVertex.num] = Math.min(dist[i][endVertex.num], endVertex.cost); // 현재 dist[i][j]와 cost를 비교해서 작은걸 대입?
                //dist[endVertex.num][i] = Math.min(dist[endVertex.num][i], endVertex.cost);
            }
        }

        for (int k = 1; k <= N; k++) { // 1~N까지 모든 정점을 거쳐가는건에 대하여
            for (int i = 1; i <= N; i++) { 
                for (int j = 1; j <= N; j++) { // i -> j까지 가는 경로중 최단경로 선택
                    // i -> j 와 i -> k -> j 중 최단경로를 대입. 그렇기 위해선 i -> j사이의 경로가 계산 되어야함 미리.
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        // 계산 끝.
        // 골때리는 문제. 양방향이 아니네...
    }
}
