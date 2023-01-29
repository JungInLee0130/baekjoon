
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int[] num;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        // 숫자 받기
        num = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int sum = 0;
        int max = 1_000_000_001;
        int min = max;

        while (start <= end) {
            if (sum >= S) {
                min = Math.min(end - start, min);
                sum -= num[start++];
            } else if (end == N) {
                break;
            } else if (sum < S) {
                sum += num[end++];
            }
        }

        if (min == max) {
            bw.write(String.valueOf(0));
        } else {
            bw.write(String.valueOf(min));
        }


        bw.flush();
        br.close();
        bw.close();
    }
}
