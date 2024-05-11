import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        T = new int[N + 1];
        price = new int[N + 1];
        for (int i = 1; i <= N; i++) { // 최대 15
            StringTokenizer st = new StringTokenizer(br.readLine());

            T[i] = Integer.parseInt(st.nextToken()); // 최대 5일
            price[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[N + 1];
        dfs(1, 0, 0, 0);

        System.out.println(max);


        bw.flush();
        bw.close();
        br.close();
    }

    static int[] T;
    static int[] price;
    static int max = 0;
    static boolean[] visited;
    private static void dfs(int start, int t, int days, int sum) { // days : 누적
        for (int i = start; i <= N; i++) {
            if (t > 0){
                t--;
                continue;
            }
            if (i + T[i] - 1 > N) continue; // N을 넘어가면 continue;
            if (!visited[i]) {
                visited[i] = true;
                dfs(i + 1, T[i] - 1, days + T[i], sum + price[i]);
                visited[i] = false;
            }
        }

        max = Math.max(max, sum);
    }
}