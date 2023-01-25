
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] array;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        array = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }


        DFS(0, 0);

        if (M == 0) {
            // 공집합 포함 : XXXXX
            answer -= 1;
        }
        bw.write(String.valueOf(answer));

        bw.flush();
        br.close();
        bw.close();
    }
    static int answer = 0;
    private static void DFS(int depth, int sum) {
        if (depth == N) {
            if (sum == M) {
                answer++;
            }
            return;
        }
        DFS(depth + 1, sum + array[depth]);
        DFS(depth + 1, sum);
    }
}
