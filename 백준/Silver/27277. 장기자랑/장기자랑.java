import org.w3c.dom.Node;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[] stats;
    private static int[] maxStats;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        stats = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            stats[i] = Integer.parseInt(st.nextToken());
        }

        maxStats = new int[N];

        Arrays.sort(stats);

        int lastIndex = N - 1;
        int startIndex = 0;
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                maxStats[i] = stats[lastIndex];
                lastIndex--;
            } else if (i % 2 != 0){
                maxStats[i] = stats[startIndex];
                startIndex++;
            }
        }

        int sum = maxStats[0];
        for (int i = 1; i < N; i++) {
            sum += Math.max(0, maxStats[i] - maxStats[i - 1]);
        }

        bw.write(String.valueOf(sum));

        bw.flush();
        br.close();
        bw.close();
    }

}
