import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] ice;
    static int year = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ice = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                ice[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (year >= 0) {
            year++;
            // 4방 탐색 : 빙산 녹이기 : 동시실행
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (ice[i][j] > 0) { // 0보다 크면 진입
                        melt(i,j);
                    }
                }
            }


            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (ice[i][j] == -1) {
                        ice[i][j] = 0;
                    }
                }
            }

            /*System.out.println("---------------- " + year + " ---------------");

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    System.out.print(ice[i][j] + " ");
                }
                System.out.println();
            }

            System.out.println();*/

            visited = new boolean[N][M];
            // 덩이 개수 계산 : bfs

            int iceCount = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (ice[i][j] > 0 && !visited[i][j]) {
                        iceCount(i, j);
                        iceCount++;
                        //System.out.println("iceCount = " + iceCount);
                    }
                }
                if (iceCount >= 2) { // 2개 이상으로 나눠지면
                    break;
                }
            }

            if (iceCount >= 2) { // 2개 이상으로 나눠지면
                break;
            }


            if (iceCount == 0) { // 아예 사라지면
                year = 0;
                break;
            }
        }

        System.out.println(year);

        bw.flush();
        br.close();
        bw.close();
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static boolean[][] visited;

    private static void melt(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int newX = x + dr[i];
            int newY = y + dc[i];
            if (ice[newX][newY] == 0) {
                ice[x][y]--;
            }
        }

        if (ice[x][y] <= 0) { // 0보다 작아지면 임시로 -1
            ice[x][y]= -1;
        }
    }

    static Queue<int[]> queue;
    private static void iceCount(int x, int y) {
        queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = poll[0] + dr[i];
                int newY = poll[1] + dc[i];

                if (0 > newX || newX > N - 1 || 0 > newY || newY > M - 1) continue;
                if (visited[newX][newY]) continue;
                if (ice[newX][newY] == 0) continue;

                queue.add(new int[]{newX, newY});
                visited[newX][newY] = true;
            }
        }
    }

}