import java.io.*;
import java.util.*;

public class Main {
    static int[] height;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        height = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        int[] num = new int[N + 2]; // + 1
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken()); // k만큼 -, +

            num[a] += k;
            num[b + 1] -= k;
        }

        int[] preSum = new int[N + 2];
        for (int i = 1; i <= N; i++) {
            preSum[i] += preSum[i - 1] + num[i];
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            height[i] += preSum[i];
            sb.append(height[i] + " ");
        }

        System.out.print(sb);


        br.close();
    }
}