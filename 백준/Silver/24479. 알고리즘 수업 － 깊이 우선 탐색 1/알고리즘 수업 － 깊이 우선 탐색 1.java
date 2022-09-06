import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[] turn;
    public static int turnVertex = 1;
    public static void main(String[] args) throws IOException {


        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 정점의 수
        int M = Integer.parseInt(st.nextToken()); // 간선의 수
        int R = Integer.parseInt(st.nextToken()); // 시작 정점

        // 인접 행렬: 메모리 초과 -> 인접 리스트
        //boolean[][] edge = new boolean[M][M]; // 0 ~ M - 1
        ArrayList<ArrayList<Integer>> edge = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            edge.add(new ArrayList<Integer>());
        }

        turn = new int[N + 1];
        Arrays.fill(turn, 0);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int startEdge = Integer.parseInt(st.nextToken());
            int endEdge = Integer.parseInt(st.nextToken());
            // 간선 집합
            //edge[startEdge - 1][endEdge - 1] = true;
            edge.get(startEdge).add(endEdge);
            edge.get(endEdge).add(startEdge);
        }

        // 오름차순 방문을위한 정렬
        for (int i = 0; i < edge.size(); i++) {
            Collections.sort(edge.get(i));
        }

        // 정렬된 엔드 노드들 확인
        /*for (int i = 0; i < edge.size(); i++) {
            for (int j = 0; j < edge.get(i).size(); j++) {
                bw.write(String.valueOf(edge.get(i).get(j)) + " ");
            }
            bw.write("\n");
        }*/

        boolean[] vertex = new boolean[N + 1];

        Arrays.fill(vertex, false);

        DFS(vertex, edge, R);

        for (int i = 1; i <= N; i++) {
            bw.write(String.valueOf(turn[i]) + "\n"); // 1부터 방문순서를 출력
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static void DFS(boolean[] visited, ArrayList<ArrayList<Integer>> edge, int start) throws IOException {
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
