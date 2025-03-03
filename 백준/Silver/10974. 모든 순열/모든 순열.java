import java.io.*;
import java.util.*;

class Main {
    static int N;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        visited = new boolean[N];
        answer = new StringBuilder();
        dfs("", 0);

        System.out.print(answer);


        br.close();
    }

    static StringBuilder answer;
    private static void dfs(String str, int depth) {
        if (depth == N) {
            answer.append(str + "\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(str + (i + 1) + " ", depth + 1);
            visited[i] = false;
        }
    }
}