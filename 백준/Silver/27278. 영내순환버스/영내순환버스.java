import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static int[] time;
    static int[] preSum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        time = new int[N + 1];
        preSum = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        preSum[1] = time[1];
        for (int i = 2; i <= N; i++) {
            preSum[i] = preSum[i - 1] + time[i];
        }

        int[] firstTime = new int[M + 1];
        int[] lastTime = new int[M + 1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());

            // 미리 나와있어야함
            if (t <= preSum[s]) {
                firstTime[i] = preSum[s];
            } else {
                int q = (t - preSum[s]) / preSum[N];
                int r = (t - preSum[s]) % preSum[N];

                firstTime[i] = preSum[s] + preSum[N] * (r > 0 ? q + 1 : q);
                /*if (r == 0) {
                    firstTime[i] = preSum[s] + preSum[lastIndex] * q;
                } else if (r > 0){
                    firstTime[i] = preSum[s] + preSum[lastIndex] * (q + 1);
                }*/
            }

            if (e > s) {
                lastTime[i] = firstTime[i] + preSum[e] - preSum[s];
            } else if (s > e && e == 0) {
                lastTime[i] = firstTime[i] + (preSum[N]) - preSum[s];
            } else {
                lastTime[i] = firstTime[i] + (preSum[N] - preSum[s]) + preSum[e];
            }
        }

        int max = 0;
        for (int i = 1; i <= M; i++) {
            max = Math.max(lastTime[i], max);
        }

        System.out.println(max);

        br.close();
    }
}
