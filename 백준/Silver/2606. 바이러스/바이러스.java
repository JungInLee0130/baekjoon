import java.io.*;
import java.util.*;

public class Main {
    static int virusCount = 0;
    private static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        boolean[] vertex = new boolean[N + 1];
        Arrays.fill(vertex, false);

        ArrayList<ArrayList<Integer>> edge = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) { // 1 ~ N
            edge.add(new ArrayList<Integer>());
        }
        
        // 그래프 작성: 인접리스트
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());

            // 무방향 그래프 (양방향 그래프)
            edge.get(startNode).add(endNode);
            edge.get(endNode).add(startNode);
        }

        // 오름차순 정렬...
        for (int i = 0; i < edge.size(); i++) {
            Collections.sort(edge.get(i));
        }
        // BFS 사용
        queue = new LinkedList<>();
        BFS(vertex, edge, 1);

        bw.write(String.valueOf(virusCount));

        bw.flush();
        br.close();
        bw.close();
    }

    private static void BFS(boolean[] visited, ArrayList<ArrayList<Integer>> edge, int start) {
        visited[start] = true;

        queue.add(start);

        while (!queue.isEmpty()) {
            int element = queue.poll();

            for (int i = 0; i < edge.get(element).size(); i++) {
                int endVertex = edge.get(element).get(i);

                if (!visited[endVertex]) {
                    virusCount++;
                    visited[endVertex] = true;
                    queue.add(endVertex);
                }
            }
        }
    }
}
