
import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Vertex>> graph = new ArrayList<>();
    static class Vertex implements Comparable<Vertex>{
        int num;
        int cost;
        int isLove;

        public Vertex(int num, int cost, int isLove) {
            this.num = num;
            this.cost = cost;
            this.isLove = isLove;
        }

        @Override
        public int compareTo(Vertex v) { // 내림차순
            if (v.isLove == this.isLove) {
                return v.cost - this.cost; // cost 내림차순
            }
            return v.isLove - this.isLove; // isLove 내림차순
        }
    }
    static int total = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 학생수 (3이상 10000이하)
        M = Integer.parseInt(st.nextToken()); // 사랑관계수 (N이상 10만이하)

        // 프림 : 최소신장트리로 시도
        // 1이면 continue; 0이면 최솟값 저장
        // 그래프 연결
        // 학생은 1부터 시작
        graph.add(new ArrayList<>());
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        int totalCost = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            // a - b : c의 애정도 d : 1(성사), 0(성사 x)
            // 1은 그대로 두고, 0 공략
            // 포기하게하는 c의 최솟값 출력

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int loveGaige = Integer.parseInt(st.nextToken());
            int isLove = Integer.parseInt(st.nextToken());

            totalCost += loveGaige;
            // 그래프 -> 프림
            graph.get(a).add(new Vertex(b, loveGaige, isLove));
            graph.get(b).add(new Vertex(a, loveGaige, isLove));
        }

        maxPrim();

        //totalCost -
        bw.write(String.valueOf(totalCost - total));


        bw.flush();
        br.close();
        bw.close();
    }

    private static void maxPrim() {
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1]; // 1 ~ N명의 학생
        priorityQueue.add(new Vertex(1, 0, 0));


        while (!priorityQueue.isEmpty()) {
            Vertex poll = priorityQueue.poll();
            int pollNum = poll.num;
            int pollCost = poll.cost;
            int pollIsLove = poll.isLove;

            // 방문 여부 검사
            if (visited[pollNum]) continue;
            visited[pollNum] = true;

            // 1인곳, 최대값 위주로 더함.
            total += pollCost;

            // 끝점 학생들과 관계
            for (Vertex endStudent : graph.get(pollNum)) {
                int endNum = endStudent.num;
                int endCost = endStudent.cost;
                int endIsLove = endStudent.isLove;

                // 방문했던거면 continue;
                if (visited[endNum]) continue;

                // 값 저장
                priorityQueue.add(endStudent);
            }
        }
    }
}

/*
 * 이미 성사된 사랑은 포기 x
 * K각 관계 x -> 이 경우 어떻게 처리?
 * 포기할수있게 하는 경우의 수 여러개 -> 애정도의 합 최소
 * 애정도의 합의 최솟값은?
 * */