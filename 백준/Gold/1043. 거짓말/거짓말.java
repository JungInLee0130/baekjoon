import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parent;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 처음으로 진실을 아는 사람이 있는 파티를 모두 구한다
        // 그 파티에 있던 사람들이 다른 파티에 있으면, 해당 하는 파티는 모두 진실이된다.
        // 그리고 그 진실된 파티에 있는 사람들 또한 또 진실을 아는 사람이 된다.
        // 그렇게 모두 걸러진 거짓된 파티 개수만 출력한다.

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int tlen = Integer.parseInt(st.nextToken());

        // 진실 아는 사람
        boolean[] person = new boolean[N + 1];
        for (int i = 1; i <= tlen; i++) {
            person[Integer.parseInt(st.nextToken())] = true;
        }

        parent = new int[N + 1]; // 1 ~ N
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }


        graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        for (int i = 1; i <= M; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {
            String[] input = br.readLine().split(" ");

            int len = Integer.parseInt(input[0]);

            if (len < 2) {
                int p = Integer.parseInt(input[1]);
                graph.get(i).add(p);
                continue;
            }
            
            for (int j = 1; j < len; j++) {
                int p1 = Integer.parseInt(input[j]); // 1
                int p2 = Integer.parseInt(input[j + 1]); // 2

                if (find(p1) != find(p2)) {
                    union(p1, p2);
                }

                graph.get(i).add(p1);
                graph.get(i).add(p2);
            }
        }

        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (person[i] && !visited[i]) { // 방문하지않은 진실을 아는사람을 만났을때
                int parent = find(i); // find하고
                for (int j = 1; j <= N; j++) {
                    if (find(j) == parent) { // 자신이랑 연결된 자식모두
                        person[j] = true; // 진실을 알고
                        visited[j] = true; // 방문한거로 침.
                    }
                }
            }
        }

        int result = 0;

        // graph : party
        for (int i = 1; i <= M; i++) {
            boolean flag = false;
            for (int p: graph.get(i)) {
                if (person[p]) // 파티에 있는 사람들 중 진실을 아는사람이 있을때
                {
                    flag = true; // 이 파티에서는 무조건 진실만 말해야함
                    break;
                }
            }

            if (!flag) result++; // 거짓말 가능한 파티++
        }

        System.out.println(result);

        bw.flush();
        br.close();
        bw.close();
    }


    private static int find(int x) {
        if (parent[x] == x) return x;
        parent[x] = find(parent[x]);
        return parent[x];
    }

    private static void union(int a, int b) {
        int parentB = find(b);
        parent[parentB] = a;
    }
}