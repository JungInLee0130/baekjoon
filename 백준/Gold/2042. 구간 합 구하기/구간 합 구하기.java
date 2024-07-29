import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static long N, M, K;
    static long[] arr; // 100만

    static final long max_N = 1_000_001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());

        // 팬윅트리할때 0들어가면 인덱스 꼬임.
        arr = new long[(int) (N + 1)];
        tree = new long[(int) (N + 1)];

        for (int i = 1; i <= N; i++) {
            // 값
            arr[i] = Long.parseLong(br.readLine());
            update(i, arr[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                update(b, c - arr[(int) b]); // b : idx, c - arr[b] : val
                arr[(int) b] = c; // 일반배열 바꿔줘야지....
            } else if (a == 2) {
                long psumS = add(b - 1);
                long psumE = add(c);
                sb.append(psumE - psumS + "\n");
            }
        }

        bw.write(String.valueOf(sb));

        bw.flush();
        br.close();
        bw.close();
    }
    
    // 주의
    // 1. 모두 걍 long으로 바꾸셈
    // 2. 값바꾼 일반배열 원소 사용하니까, 갱신해줘야함.

    private static long add(long pos) {
        long res = 0;
        while (pos > 0) {
            res += tree[(int) pos]; // tree값더하고
            pos &= (pos - 1); // 1뺀거랑 AND 연산
        }
        return res;
    }

    static long[] tree;

    private static void update(long pos, long val) {
        while (pos <= N) { // idx는 N - 1까지
            tree[(int) pos] += val;
            pos += (pos & -pos); // 모두 더해주기
        }
    }
}