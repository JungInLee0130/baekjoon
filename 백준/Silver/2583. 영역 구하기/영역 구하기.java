import java.io.*;
import java.util.*;

public class Main {
    static int R,C, K;
    static int[][] rec;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        rec = new int[K][4];

        // 좌표 입력
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                // x1,y1,x2,y2
                rec[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1로 칠하기 : 직사각형

        paint();

        // 직사각형 개수세기, 면적 구하기
        count();

        print();


        bw.flush();
        br.close();
        bw.close();
    }

    private static void print() {
        System.out.println(size.size());
        Collections.sort(size);
        for (int i = 0; i < size.size(); i++) {
            System.out.print(size.get(i) + " ");
        }
    }

    static ArrayList<Integer> size;

    private static void count() {
        size = new ArrayList<>();
        visited = new boolean[R][C];

        for (int c = 0; c < C; c++) {
            for (int r = 0; r < R; r++) {
                if (map[r][c] == 0 && !visited[r][c]) { // 0이고 미방문
                    size.add(bfs(r, c));
                }
            }
        }
    }

    static Queue<int[]> queue;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    private static int bfs(int x, int y) {
        queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        int count = 1;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = poll[0] + dr[i];
                int ny = poll[1] + dc[i];

                // 범위 x, 이미 방문함, 1임
                if (0 > nx || nx > R - 1 || 0 > ny || ny > C - 1) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == 1) continue;

                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
                count++;
            }
        }
        
        return count;
    }

    private static void paint() {
        for (int i = 0; i < K; i++) {
            for (int r = R - rec[i][3]; r < R - rec[i][1]; r++) {
                for (int c = rec[i][0]; c < rec[i][2]; c++) {
                    map[r][c] = 1;
                }
            }
        }
    }

}