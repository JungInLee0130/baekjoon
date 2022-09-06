import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static Queue<Integer> queue;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        boolean[] vertex = new boolean[N + 1]; // 1 ~ N
        Arrays.fill(vertex, false);
        
        // 그래프 간선 : 인접리스트 초기화
        ArrayList<ArrayList<Integer>> edge = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            edge.add(list);
        }
        
        // 간선 연결: 무방향
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());

            edge.get(startNode).add(endNode);
            edge.get(endNode).add(startNode);
        }

        // 간선 정렬(오름차순)
        for (int i = 0; i < edge.size(); i++) {
            Collections.sort(edge.get(i));
        }

        // BFS: 큐
        queue = new LinkedList<>();

        bw.write(String.valueOf(V) + " ");
        DFS(vertex, edge, V);
        bw.write("\n");
        BFS(vertex, edge, V);
        bw.write("\n");

        bw.flush();
        br.close();
        bw.close();
    }

    private static void DFS(boolean[] visited, ArrayList<ArrayList<Integer>> edge, int start) throws IOException {
        visited[start] = true;

        for (int i = 0; i < edge.get(start).size(); i++) {
            int endVertex = edge.get(start).get(i);

            if (!visited[endVertex]) {
                visited[endVertex] = true;

                bw.write(String.valueOf(endVertex) + " ");

                DFS(visited, edge, endVertex);
            }
        }
    }

    private static void BFS(boolean[] visited, ArrayList<ArrayList<Integer>> edge, int start) throws IOException {
        Arrays.fill(visited, false);

        visited[start] = true;

        bw.write(String.valueOf(start) + " ");

        queue.add(start);

        while (!queue.isEmpty()) {
            int element = queue.poll();

            for (int i = 0; i < edge.get(element).size(); i++) {
                int endVertex = edge.get(element).get(i); // endVertex 추출

                if (!visited[endVertex]) {
                    visited[endVertex] = true;
                    bw.write(String.valueOf(endVertex) + " ");

                    queue.add(endVertex);
                }
            }
        }
    }
}
