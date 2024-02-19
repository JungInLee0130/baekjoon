import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int T, A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        // A -> B : 최소한의 명령어, 가능한게 여러개면 아무거나

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            min = Integer.MAX_VALUE;
            String str = bfs(A, B);

            if (str.length() > 0) {
                System.out.println(str);
            }
        }
    }
    static Queue<Point> queue;
    static int min = Integer.MAX_VALUE;
    static class Point{
        int num;
        String order;

        public Point(int num, String order) {
            this.num = num;
            this.order = order;
        }
    }
    static boolean[] visited;
    private static String bfs(int a, int b) {
        queue = new LinkedList<>();
        queue.add(new Point(a, ""));
        visited = new boolean[10000];
        visited[a] = true;

        while (!queue.isEmpty()) {
            Point poll = queue.poll();

            if (poll.num == b) {
                return poll.order;
            }

            char[] nxc = {'D', 'S', 'L', 'R'};
            int[] nx = new int[4];
            nx[0] = 2 * poll.num % 10000;
            nx[1] = poll.num - 1;

            int thou = poll.num / 1000; // 4
            int hun = poll.num % 1000 / 100; // 3
            int ten = poll.num % 1000 % 100 / 10; // 2
            int one = poll.num % 1000 % 100 % 10; // 1
            // if L
            nx[2] = hun * 1000 + ten * 100 + one * 10 + thou;
            // if R
            nx[3] = one * 1000 + thou * 100 + hun * 10 + ten;


            if (nx[1] == -1) {
                nx[1] = 9999;
            }

            for (int i = 0; i < 4; i++) {
                int next = nx[i];

                if (visited[next]) continue;
                visited[next] = true;
                // copy and 대입
                String str = poll.order;
                str += nxc[i];
                queue.add(new Point(next, str));
            }
        }
        return "";
    }
}