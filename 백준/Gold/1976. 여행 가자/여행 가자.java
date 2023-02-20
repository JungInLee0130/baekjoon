import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] graph;
    static int[] plan;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 200이하
        M = Integer.parseInt(br.readLine()); // 1000이하

        // 인접 그래프
        graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 여행계획
        plan = new int[M + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

        // 방문했던 부분 방문해서 너무 커짐.
        // 여부만 판단하므로, 유니온파인드를 사용해도됨.
        // 사실 경로 파악해도 사용해도될듯.
        parent = new int[N + 1]; // 1 ~ N

        // 부모노드 자기자신으로 초기화
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 각각의 부모노드 구하기
        for (int i = 1; i <= N; i++) {
            int from = fromParent(i);
            for (int j = i + 1; j <= N; j++) {
                if (graph[i][j] == 1) {
                    int to = fromParent(j);
                    if (from == to) continue;
                    parent[to] = from; // union
                }
            }
        }

        int temp = fromParent(plan[1]);
        boolean istrue = true;
        for (int i = 2; i <= M; i++) {
            if (temp == fromParent(plan[i])) continue;
            istrue = false;
        }
        if (istrue) {
            bw.write("YES");
        }
        else {
            bw.write("NO");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static int[] parent;
    private static int fromParent(int node) {
        if (parent[node] == node) return node;
        return parent[node] = fromParent(parent[node]);
    }
}

/*
* 1. 도시들의 개수(200)
* 2. 여행 계획에 속한 도시들의 수(M)
* 3. N x N 행렬 : (i,j) : 연결 여부
* A,B가 연결되어있으면 B,A도 연결
* 4. 여행 계획
* 도시번호는 1~N까지
* 가능한 여행 계획인지 판별하는 프로그램 작성 : 가능하면 yes, 불가능하면 no
* 같은 도시 여러번 방문 가능.
* */