import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1]; // 0 ~ n까지 부모 확인
        for (int i = 0; i <= n; i++) {
            parent[i] = i; // 자기 자신으로
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            int fromParent = findParent(from);
            int toParent = findParent(to);

            if (command == 0) { // 합함.
                union(fromParent, toParent);
            }
            if (command == 1) { // 확인. -> 부모노드 같은지 확인
                if(find(fromParent, toParent)){
                    bw.write("YES\n");
                }
                else{
                    bw.write("NO\n");
                }

            }
        }


        bw.flush();
        br.close();
        bw.close();
    }

    private static void union(int fromParent, int toParent) {
        if (fromParent == toParent) return;
        parent[toParent] = fromParent; // 부모노드 갱신
    }

    private static boolean find(int fromParent, int toParent) {
        if (fromParent == toParent) {
            return true;
        } else {
            return false;
        }
    }

    private static int findParent(int node) {
        if (parent[node] == node) return node; // 끝 도달
        return parent[node] = findParent(parent[node]); // 부모노드로 계속 재귀.
    }
}