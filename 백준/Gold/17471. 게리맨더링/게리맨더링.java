import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    private static int[] population;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        population = new int[N];
        for (int i = 0; i < N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }
        // 집합 1, 2
        arr1 = new int[N];
        arr2 = new int[N];

        // 연결
        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            for (int j = 0; j < len; j++) {
                int start = i;
                int end = Integer.parseInt(st.nextToken()) - 1;

                graph.get(start).add(end);
                graph.get(end).add(start);
            }
        }

        visited = new boolean[N];
        // 부분집합
        min = Integer.MAX_VALUE;
        subset(0);

        if (min == Integer.MAX_VALUE) {
            bw.write(String.valueOf(-1));
        }
        else {
            bw.write(String.valueOf(min));
        }



        bw.flush();
        br.close();
        bw.close();
    }

    private static boolean[] visited; // 부분 집합용 배열
    private static int[] arr1;
    private static int[] arr2;
    private static int min;
    private static void subset(int cnt) {
        if (cnt == N) {
            // 두집합 나누기
            int len1 = 0;
            int len2 = 0;
            for (int i = 0; i < N; i++) {
                if (visited[i]) arr1[len1++] = i;
                else arr2[len2++] = i;
            }

            // bfs
            bfsVisited = new boolean[N];
            bfsA(arr1[0]);
            bfsB(arr2[0]);

            // 둘다 모두 true 인지 확인
            for (int i = 0; i < N; i++) {
                if(!bfsVisited[i]) return;
            }

            int sum1 = 0;
            for (int i = 0; i < len1; i++) {
                sum1 += population[arr1[i]];
            }
            int sum2 = 0;
            for (int i = 0; i < len2; i++) {
                sum2 += population[arr2[i]];
            }

            min = Math.min(min, Math.abs(sum2 - sum1));

            return;
        }

        visited[cnt] = true;
        subset(cnt + 1); // 1,2,3,4,5 -> 1,2,3 / 4,5
        // 1,2,4 / 3,5

        visited[cnt] = false;
        subset(cnt + 1);
    }


    private static boolean[] bfsVisited; // bfs용 배열
    private static void bfsA(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        bfsVisited[start] = true; // 먼저 visited

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            for (int end : graph.get(poll)) {
                if (bfsVisited[end]) continue; // 방문하지 않았어야함.
                if (!visited[end]) continue; // A원소 여야함.
                bfsVisited[end] = true;
                queue.add(end);
            }
        }
    }

    private static void bfsB(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        bfsVisited[start] = true;

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            for (int end : graph.get(poll)) {
                if (bfsVisited[end]) continue; // 방문하지 않았어야함.
                if (visited[end]) continue; // B(false)원소 여야함.
                bfsVisited[end] = true;
                queue.add(end);
            }
        }
    }
}