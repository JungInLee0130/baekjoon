import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // 양방향 그래프 : 4개 연속 관계를 갖는것 찾으면될듯
            graph.get(start).add(end);
            graph.get(end).add(start);
        }


        // 1, 0
        // 길이가 5인거 찾아야함. 최단거리 아님
        isTrue = false;
        for (int i = 0; i < N; i++) {
            if (graph.get(i).size() > 0) { // 간선이 있는것만 순회
                visited = new boolean[N];
                visited[i] = true;
                dfs(i, 0);
                if (isTrue) {
                    break;
                }
            }
        }

        if (isTrue) {
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }


        bw.flush();
        br.close();
        bw.close();
    }

    static boolean isTrue;
    static boolean[] visited;
    private static void dfs(int i, int count) {
        //if (isTrue) return;

        if (count == 4) {
            isTrue = true;
            return;
        }

        for (Integer end : graph.get(i)) {
            if (!visited[end]) {
                visited[end] = true;
                dfs(end, count + 1);
                visited[end] = false;
            }
        }

    }

}