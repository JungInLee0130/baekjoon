import java.io.*;
import java.util.*;

class Main {
    static int N, M, B;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        int low = 256;
        int high = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                high = Math.max(high, map[i][j]);
                low = Math.min(low, map[i][j]);
            }
        }

        for (int i = low; i <= high; i++) {
            calculateTime(i);
        }

        System.out.println(minTime + " " + maxHeight);

        br.close();
    }

    static int minTime = Integer.MAX_VALUE;
    static int maxHeight = 0;

    private static void calculateTime(int height) {
        int time = 0;
        int b = B;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int value = map[i][j] - height;

                if (value > 0){
                    time += 2 * value;
                    b += value;
                }
                else{
                    time += 1 * -value;
                    b -= -value;
                }
            }
        }

        if (b >= 0) {
            if (minTime >= time) {
                minTime = time;
                if (maxHeight < height) {
                    maxHeight = height;
                }
            }
        }
    }
}