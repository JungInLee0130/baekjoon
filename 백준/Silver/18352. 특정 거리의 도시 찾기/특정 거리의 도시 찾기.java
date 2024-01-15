import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, X;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        graph.add(new ArrayList<>()); // 0
        for (int i = 0; i < N; i++) { // 1 ~ N
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // 단방향
            graph.get(start).add(end);
        }

        // 출발도시 X부터 거리 K에 해당되는 모든 도로 출력
        bfs(X);


        for (Integer e: arrayList) {
            System.out.println(e);
        }



        bw.flush();
        br.close();
        bw.close();
    }

    static Queue<int[]> queue;
    static ArrayList<Integer> arrayList;
    static boolean[] visited;
    private static void bfs(int x) {
        queue = new LinkedList<>();
        arrayList = new ArrayList<>();
        visited = new boolean[N + 1];
        queue.add(new int[]{x, 0});
        visited[x] = true;

        boolean isK = false;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            if (poll[1] > K) {
                break;
            }

            if (poll[1] == K) {
                arrayList.add(poll[0]);
                isK = true;
            }

            for (Integer end :graph.get(poll[0])) {
                if(!visited[end]) {
                    visited[end] = true;
                    queue.add(new int[]{end, poll[1] + 1});
                }
            }
        }

        Collections.sort(arrayList);

        if (!isK) {
            System.out.println(-1);
        }
    }
}