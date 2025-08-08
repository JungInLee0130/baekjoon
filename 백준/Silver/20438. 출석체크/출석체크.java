import java.io.*;
import java.util.*;

public class Main {
    static boolean[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new boolean[N + 3]; // ~ N + 2

        st = new StringTokenizer(br.readLine());

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(st.nextToken());

            set.add(num);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (set.contains(num)) continue;
            
            for (int j = num; j <= N + 2; j += num) {
                if (set.contains(j)) {
                    continue;
                }
                arr[j] = true;
            }
        }

        int[] preSum = new int[N + 3]; // 출석 x의 합

        for (int i = 3; i <= N + 2; i++) {
            if (!arr[i]) {
                preSum[i] = preSum[i - 1] + 1; // 출석 x인것만 카운트
                continue;
            }
            preSum[i] = preSum[i - 1]; // 출석 x인것만 카운트
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            System.out.println(preSum[e] - preSum[s - 1]);
        }

        //System.out.print(sb);

        br.close();
    }
}