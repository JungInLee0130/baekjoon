import java.io.*;
import java.util.*;

class Main {
    static int N, K;
    static boolean[][] visited = new boolean[2][500001]; // 0 : 짝, 1 : 홀
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited[0][N] = true;

        if (N == K) {
            System.out.println(0);
            return;
        }

        System.out.println(bfs());

        br.close();
    }
    static class Point {
        int x;
        int time;

        public Point(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }
    private static int bfs() {
        queue.add(new Point(N, 0));

        kx = K;

        while (!queue.isEmpty()) {
            Point poll = queue.poll();

            int x = poll.x;
            int t = poll.time;

            if (kSecond == t){
                kSecond++;
                kx += kSecond;
                if (!isRange(kx)) {
                    return -1;
                }
            }

            int result = move(x - 1, t + 1);
            if (result >= 0) return result;

            result = move(x + 1, t + 1);
            if (result >= 0) return result;

            result = move(x * 2, t + 1);
            if (result >= 0) return result;
        }

        return -1;
    }
    static Queue<Point> queue = new LinkedList<>();;
    static int kSecond = 0;

    static int kx;
    private static int move(int num, int t) {
        int n = num;

        if (!isRange(n)) return -2; // -2 : 범위 밖

        // 검증용
        if (kx >= 52){
            //System.out.println(kx);
        }

        if (!visited[t % 2][n]) {
            visited[t % 2][n] = true;
            queue.add(new Point(n, t));
        }

        if (visited[kSecond % 2][kx]) {
            return kSecond;
        }

        return -3;
    }
    private static boolean isRange(int pos) {
        if (MIN <= pos && pos <= MAX) return true;
        return false;
    }

    static final int MIN = 0;
    static final int MAX = 500_000;
}
