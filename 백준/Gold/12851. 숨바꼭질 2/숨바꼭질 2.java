import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs(); // min
        //arr = new int[min + 1];
        //dfs(N, K, 0, min); // count

        System.out.println(min);
        System.out.println(count);
    }

    private static int[] arr;
    private static void dfs(int start, int end, int depth, int l) {
        if (depth > l) return;

        if (start == end) {
            for (int i = 0; i <= l; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            count++;
            return;
        }

        arr[depth] = start;
        dfs(start + 1, end, depth + 1, l);
        arr[depth] = start;
        dfs(start - 1, end, depth + 1, l);
        arr[depth] = start;
        dfs(start * 2, end, depth + 1, l);
    }


    static Queue<int[]> queue;
    static int[] visit; // int로 visited 체크, 중복 고려해야하기 때문
    static int min = Integer.MAX_VALUE;
    static int count = 0;

    private static void bfs() {
        queue = new LinkedList<>();
        visit = new int[200000];
        queue.add(new int[]{N, 0});
        visit[N] = 1; // 방문 함

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int curX = poll[0];
            int time = poll[1];

            if (curX == K) {
                if (min == Integer.MAX_VALUE) {
                    min = time;
                }
                if (min == time){
                    count++;
                }
                continue;
            }

            int[] nx = {curX + 1, curX - 1, curX * 2};

            for (int i = 0; i < 3; i++) {
                int next = nx[i];
                if (next < 0) continue;
                if (next >= 200000) continue;
                // 중복은 제거한다.
                // 근데 신경쓰이는게 완전히 똑같은 경로일때 다른 경우의 수
                // 따라서 time + 1인경우도 제외
                // 방문하지 않은경우는 그냥 들어가고
                if (visit[next] == 0 || visit[next] == time + 1) {
                    visit[next] = time + 1;
                    queue.add(new int[]{next, time + 1});
                }
            }
        }
    }
}