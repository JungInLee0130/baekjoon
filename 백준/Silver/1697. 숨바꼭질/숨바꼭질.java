import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int depth = 0;
    private static boolean[] visited = new boolean[200001];
    private static int[] line = new int[200001];
    private static Queue<Integer> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 0 <= N <= 100000, 0 <= K <= 100000
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N == K) {
            //depth == 0
        } else {
            BFS(N, K);
        }

        // 걷: 1칸 이동, 순: 2배 이동
        bw.write(String.valueOf(depth));

        bw.flush();
        br.close();
        bw.close();
    }

    // depth 관리
    private static void BFS(int N, int K) {
        visited[N] = true;
        queue.add(N);
        int queueSize = 1;
        int childNum = 1;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            queueSize = queueSize - 1;
            if (queueSize == 0) {
                queueSize = childNum;
                childNum = 0;
                depth++;
            }
            if (0 <= poll && poll <= 100000) {
                int num1 = poll - 1;
                int num2 = poll + 1;
                int num3 = poll * 2;
                // 100000 > 이면 nothing
                // 0 <= poll <= 100000
                // 0 <= poll - 1 <= 99999  => 1 <= poll <= 100000
                // 1 <= poll + 1 <= 100000 => 0 <= poll <= 99999
                // 0 <= poll * 2 <= 200000 => 0 <= poll <= 50000
                //

                if (num1 >= 0) { // poll - 1 >= 0
                    if (!visited[num1]) {
                        visited[num1] = true;
                        if (num1 == K) {
                            return;
                        } else {
                            childNum++;
                            queue.add(num1);
                        }
                    }
                }
                if (!visited[num2]) {
                    visited[num2] = true;
                    if (num2 == K) {
                        return;
                    } else {
                        childNum++;
                        queue.add(num2);
                    }
                }
                if (!visited[num3]) {
                    visited[num3] = true;
                    if (num3 == K) {
                        return;
                    } else {
                        childNum++;
                        queue.add(num3);
                    }
                }
            }
        }
    }
}
