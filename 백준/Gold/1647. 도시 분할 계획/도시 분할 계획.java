import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    // 최소 신장 트리 : 간선만을 선택하는 방법.
    // 사이클 여부를 알아보기위해 유니온파인드를 사용
    static int[][] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new int[M][3];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[i][0] = start;
            edges[i][1] = end;
            edges[i][2] = weight;
        }

        // minimum spanning tree
        parent = new int[N + 1]; // 1 ~ N
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 1. 가중치 오름차순으로 정렬
        Arrays.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });

        int total = 0;
        int count = 0; // N - 1, N - 2 일때 더하지않음

        for (int i = 0; i < M; i++) {
            if (find(edges[i][0]) != find(edges[i][1])) {
                total += edges[i][2];
                union(edges[i][0], edges[i][1]);
                if (count == N - 2) {
                    total -= edges[i][2];
                }
                count++;
            }
        }

        System.out.println(total);

    }

    static int[] parent;

    private static void union(int a, int b) {
        int a_parent = find(a);
        int b_parent = find(b);

        if (a_parent < b_parent) {
            parent[b_parent] = a_parent;
        }
        else{
            parent[a_parent] = b_parent;
        }
    }

    private static int find(int i) {
        if (parent[i] == i) return i;
        return find(parent[i]);
    }
}