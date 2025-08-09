import java.io.*;
import java.util.*;

public class Main {
    static int[] hard;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        hard = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());

            hard[i] = num;
        }

        int[] times = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (hard[i - 1] <= hard[i]) {
                times[i] = times[i - 1];
            }
            else {
                times[i] = times[i - 1] + 1;
            }
        }

        int Q = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 실수를 하게될 횟수 출력
            sb.append(times[y] - times[x] + "\n");
        }

        System.out.print(sb);

        br.close();
    }
}