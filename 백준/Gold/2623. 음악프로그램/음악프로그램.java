import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] indegree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 가수의 수
        M = Integer.parseInt(st.nextToken()); // 보조 pd 수

        graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        indegree = new int[N + 1]; // 1 ~ N;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            ArrayList<Integer> vertexs = new ArrayList<>();

            int len = Integer.parseInt(st.nextToken());

            for (int j = 0; j < len; j++) {
                vertexs.add(Integer.parseInt(st.nextToken()));
            }

            // 그래프 연결
            len = vertexs.size();
            for (int j = 0; j < len - 1; j++) {
                int start = vertexs.get(j);
                int end = vertexs.get(j + 1);
                graph.get(start).add(end);
                indegree[end]++;
            }
        }

        // 연결 끝나면 위상 정렬
        topologiSort();


        bw.flush();
        bw.close();
        br.close();
    }

    static ArrayList<Integer> answer = new ArrayList<>();

    private static void topologiSort() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.add(i); // 인접차수가 0인것 queue에 넣기
            }
        }

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            answer.add(poll);

            for (Integer e :graph.get(poll)) {
                indegree[e]--;
                if (indegree[e] == 0) {
                    queue.add(e);
                }
            }
        }

        if (answer.size() != N) {
            System.out.println(0);
            return;
        }

        answer.forEach(e -> System.out.println(e));
    }
}