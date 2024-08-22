import java.io.*;
import java.util.*;

public class Main {
    static int N, S, M;
    static int[] seconds;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        S = N - S; // left

        M = Integer.parseInt(br.readLine());

        seconds = new int[M];
        for (int i = 0; i < M; i++) { // 10만이하
            seconds[i] = Integer.parseInt(br.readLine()); // 1000이하
        }

        int t = 0;
        int count = 0;
        int idx = 0;
        while (S > 0) {
            for (int i = 0; i < M; i++) {
                if (t % seconds[i] == 0) {
                    S--;
                    idx = i;
                    if (S == 0) {
                        break;
                    }
                }
            }
            t++;
        }

        // -1 나오는 경우는 없음.
        System.out.println(idx + 1);

    }
}