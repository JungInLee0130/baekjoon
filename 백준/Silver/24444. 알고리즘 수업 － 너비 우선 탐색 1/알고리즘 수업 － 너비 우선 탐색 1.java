import java.io.*;
import java.util.*;

public class Main {

    static int[] turn;
    static int turnVertex = 1;
    static Queue<Integer> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        boolean[] vertex = new boolean[N + 1]; // 1~N까지 인덱스 사용
        Arrays.fill(vertex, false);

        ArrayList<ArrayList<Integer>> edge = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            edge.add(list);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());

            edge.get(startNode).add(endNode);
            edge.get(endNode).add(startNode);
        }

        // 오름차순 정렬

        for (int i = 1; i <= N; i++) {
            Collections.sort(edge.get(i));
        }

        turn = new int[N + 1]; // 1~N까지 인덱스 사용
        Arrays.fill(turn, 0);

        queue = new LinkedList<>();
        BFS(vertex, edge, R);

        for (int i = 1; i <= N; i++) { // 1 ~ N까지
            bw.write(String.valueOf(turn[i]) + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    public static void BFS(boolean[] visited, ArrayList<ArrayList<Integer>> edge, int start) { // 재귀 x. 반복

        /*Arrays.fill(visited, false);*/

        visited[start] = true;

        turn[start] = turnVertex;

        queue.add(start); // 큐 뒤에 시작정점 대입

        while (!queue.isEmpty()) {
            int front = queue.poll(); // 큐 맨 앞쪽의 요소 제거

            for (int i = 0; i < edge.get(front).size(); i++) { // front의 인접 정점들 중에서
                int endVertex = edge.get(front).get(i);

                if (!visited[endVertex]) {
                    turnVertex++;

                    turn[endVertex] = turnVertex;

                    visited[endVertex] = true;

                    queue.add(endVertex);
                }
            }
        }
    }
}
