import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int[][] weight = new int[E][3]; // 0 : 시작 1 : 도착 2 : 가중치
        // 가중치 합이 최소니까 크루스칼?
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            weight[i][0] = Integer.parseInt(st.nextToken());
            weight[i][1] = Integer.parseInt(st.nextToken());
            weight[i][2] = Integer.parseInt(st.nextToken());
        }

        // 가중치 오름차순 정렬
        Arrays.sort(weight, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        // 예는 부모노드가 같은거만 나왔지만 다른게 나온다면?
        // 유니온 파인드
        parent = new int[V + 1]; // 1 ~ V

        // 부모노드 모두 자신으로 초기화
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        // 최소 가중치로 연결할수있는 가중치의 총 합
        int total = 0;

        // edge순회 : edge중에서
        for (int[] edge : weight) {
            // 각각의 정보 저장
            int from = edge[0];
            int to = edge[1];
            int cost = edge[2];

            // 두 정점의 부모를 찾는다.
            int fromParent = findParent(from);
            int toParent = findParent(to);

            // 같은 그래프에 속함 (두 부모노드가 같으면)
            if (fromParent == toParent) continue; // 연결하지않는다.
            
            total += cost;
            // 다른 그래프면 도착점에 시작점을 연결
            parent[toParent] = fromParent;
        }

        bw.write(String.valueOf(total));


        bw.flush();
        br.close();
        bw.close();
    }

    private static int findParent(int node) {
        // parent[node]가 node와 같아질때 return node.
        if(parent[node] == node) return node;
        return parent[node] = findParent(parent[node]);
    }
}