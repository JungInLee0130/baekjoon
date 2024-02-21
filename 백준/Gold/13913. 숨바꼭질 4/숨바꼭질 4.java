import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[200001];

        min = bfs();

        Stack<Integer> stack = new Stack<>();
        stack.push(K);
        int index = K;

        while (index != N) {
            stack.push(arr[index]);
            index = arr[index];
        }

        System.out.println(min);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    static int[] arr;

    static class Point{
        int num;
        int time;

        public Point(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }
    static int min = Integer.MAX_VALUE;
    static boolean[] visited;
    private static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(N, 0));
        visited = new boolean[200001]; // 1 ~ 200000
        visited[N] = true;

        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int curX = poll.num;
            int time = poll.time;

            int[] nx = {curX - 1, curX + 1, curX * 2};
            for (int i = 0; i < 3; i++) {
                int newX = nx[i];
                if (newX < 0) continue;
                if (newX > 200000) continue;
                if (visited[newX]) continue;

                visited[newX] = true;
                Point point = new Point(newX, time + 1);
                arr[newX] = curX;

                if (newX == K) {
                    return time + 1;
                }

                queue.add(point);
            }
        }
        return 0;
    }
}