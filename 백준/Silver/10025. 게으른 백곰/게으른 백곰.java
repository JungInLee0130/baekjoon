import java.io.*;
import java.util.*;

class Main {
    static int N, K;
    static int[] G;
    static int[] X;
    static class Ice{
        int g;
        int x;

        public Ice(int g, int x) {
            this.g = g;
            this.x = x;
        }
    }
    static Ice[] ices;
    static final int MAX_SIZE = 1_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int max = 0;
        G = new int[MAX_SIZE + 1];
        boolean[] hasValue = new boolean[MAX_SIZE + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int g = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            G[x] = g;
            hasValue[x] = true;
            max = Math.max(max, x);
        }

        int left = 0;
        int right = 2 * K;

        int sum = 0;
        for (int i = 0; i < 2 * K + 1; i++) {
            if (i > MAX_SIZE) break;
            if (hasValue[i]) sum += G[i];
        }

        int answer = sum;

        while (left - 1 <= max && right - 1 <= max){
            int sub = right - left;

            if (sub < 2 * K) {
                if (hasValue[right + 1]){
                    sum += G[right + 1];
                }
                right += 1;
            } else if (sub > 2 * K) {
                if (hasValue[left]){
                    sum -= G[left];
                }
                left += 1;
            }

            if (sub == 2 * K){
                answer = Math.max(sum, answer);

                if (hasValue[left]){
                    sum -= G[left];
                }

                left += 1;
            }
        }

        System.out.println(answer);


        br.close();
    }
}