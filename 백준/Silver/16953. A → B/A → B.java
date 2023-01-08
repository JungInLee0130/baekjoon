import java.io.*;
import java.math.BigInteger;
import java.util.*;

class Point {
    long num;
    int depth;

    public Point(long num, int depth) {
        this.num = num;
        this.depth = depth;
    }

    public long getNum() {
        return num;
    }

    public int getDepth() {
        return depth;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int bfs = BFS(A, B);

        if (bfs == -1) {
            bw.write(String.valueOf(-1));
        } else {
            bw.write(String.valueOf(bfs + 1));
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static int BFS(int start, int end) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(start, 0));

        while (!queue.isEmpty()) {
            Point poll = queue.poll();

            long[] newNum = new long[2];
            newNum[0] = poll.num * 2;
            newNum[1] = poll.num * 10 + 1;
            for (int i = 0; i < 2; i++) {
                if (1 <= newNum[i] && newNum[i] <= end) { // 미방문, 범위내
                    if (newNum[i] == end) {
                        return poll.depth + 1;
                    }
                    queue.add(new Point((int) newNum[i], poll.depth + 1));
                }
            }
        }
        return -1;
    }
}
