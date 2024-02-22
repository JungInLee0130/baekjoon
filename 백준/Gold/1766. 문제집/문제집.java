import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int[] indegree;
    static int N, M;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        indegree = new int[N + 1]; // 1 ~ N
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph.get(start).add(end);
            indegree[end]++; // 진입차수 올려줌
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 위상정렬 + pq : pq가 넣자마자 정렬을 해줌

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                pq.add(i);
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();

        while (!pq.isEmpty()) {
            Integer poll = pq.poll();

            answer.add(poll);

            for (Integer e :graph.get(poll)) {
                indegree[e]--;

                if (indegree[e] == 0) {
                    pq.add(e);
                }
            }
        }

        answer.forEach(integer -> System.out.print(integer + " "));
    }
}