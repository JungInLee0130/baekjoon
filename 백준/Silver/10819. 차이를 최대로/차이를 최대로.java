import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[N];
        arr = new int[N];
        dfs(0);

        System.out.println(max);

        br.close();
    }

    static boolean[] visited;
    static int[] arr;
    static int max = -800;
    private static void dfs(int idx) {
        if (idx == N){
            int sum = 0;
            for (int i = 1; i < N; i++) {
                sum += Math.abs(arr[i - 1] - arr[i]);
            }
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            arr[idx] = A[i];
            dfs(idx + 1);
            visited[i] = false;
        }
    }
}