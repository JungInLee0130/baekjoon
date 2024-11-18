import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static int N, M;
    static int[][] arr, sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][M + 1];
        sum = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                sum[i][j] = arr[i][j] + sum[i][j - 1]; // 행의합구하기
            }
        }

        // sum은 각 row의 합을 담고있다.

        StringBuilder sb = new StringBuilder();
        int K = Integer.parseInt(br.readLine());
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int s = 0;
            for (int i = x1; i <= x2; i++) {
                s += sum[i][y2] - sum[i][y1 - 1];
            }

            sb.append(s + "\n");
        }

        System.out.println(sb);

        bw.flush();
        bw.close();
        br.close();
    }
}