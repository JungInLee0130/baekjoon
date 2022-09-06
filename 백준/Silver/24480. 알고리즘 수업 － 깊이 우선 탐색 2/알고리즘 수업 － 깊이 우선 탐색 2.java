import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    
    static int[] turn;
    static int turnVertex = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> edge = new ArrayList<>(); // 간선배열: 인접리스트로 해결

        for (int i = 0; i < N + 1; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            edge.add(list);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());

            // 무방향 그래프 (양방향)
            edge.get(startNode).add(endNode);
            edge.get(endNode).add(startNode);
        }

        // 내림차순으로 방문 -> Collections.sort(Reverse.order)

        for (int i = 0; i < edge.size(); i++) {
            Collections.sort(edge.get(i), Collections.reverseOrder());
        }

        boolean[] vertex = new boolean[N + 1]; // 정점 배열
        Arrays.fill(vertex, false); // 처음엔 모두 방문하지 않았으므로

        turn = new int[N + 1]; // 방문 순서 배열
        Arrays.fill(turn, 0); // 0으로 초기화
        
        DFS(vertex, edge, R);

        for (int i = 1; i <= N; i++) {
            bw.write(String.valueOf(turn[i]) + "\n"); // 1부터 방문순서를 출력
        }

        bw.flush();
        br.close();
        bw.close();
    }

    public static void DFS(boolean[] visited, ArrayList<ArrayList<Integer>> edge, int start) throws IOException {
        visited[start] = true;

        turn[start] = turnVertex; // 처음 start에서 방문순서를 대입.

        for (int i = 0; i < edge.get(start).size(); i++) { // 정점 start의 인접 정점 집합
            int endVertex = edge.get(start).get(i); // 끝 정점
            if (!visited[endVertex]) { // 방문하지 않았다면
                turnVertex++;
                DFS(visited, edge, endVertex);
            }
        }
    }
}
