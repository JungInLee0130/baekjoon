import java.io.*;
import java.util.*;

class Main {
    static int N, Q;
    static int len;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        len = (int) Math.pow(2, N);
        map = new int[len][len];

        for (int r = 0; r < len; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < len; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        String[] split = br.readLine().split(" ");

        stepQue = new LinkedList<>();

        for (int i = 0; i < split.length; i++) {
            stepQue.add(Integer.parseInt(split[i]));
        }

        solve();

        br.close();
    }

    static Queue<Integer> stepQue;

    private static void solve() {
        while (!stepQue.isEmpty()) {
            Integer level = stepQue.poll();

            int smallLen = (int) Math.pow(2, level);

            int r = 0;
            int c = 0;

            // 1. 회전 다하기
            while (r < len){
                c = 0;
                while (c < len) {
                    // 회전하기
                    rotate90(r, c, smallLen);
                    c += smallLen;
                }
                r += smallLen;
            }

            // 2. 녹이기 (그냥 각각의 칸에 대해서 하면됨.)
            melt();
        }

        // 1. 남은 아이스 구하기
        int sum = 0;
        for (int r = 0; r < len; r++) {
            for (int c = 0; c < len; c++) {
                if(map[r][c] <= 0) continue;
                sum += map[r][c];
            }
        }

        visited = new boolean[len][len];
        flood = new int[len][len];
        int num = 1;

        for (int r = 0; r < len; r++) {
            for (int c = 0; c < len; c++) {
                if (visited[r][c]) continue;
                if (map[r][c] > 0){
                    visited[r][c] = true;
                    floodfill(r, c, num);
                    num++;
                }
            }
        }

        int max = 0;
        for (Map.Entry<Integer,Integer> entry : hashMap.entrySet()) {
            max = Math.max(entry.getValue(), max);
        }

        System.out.println(sum);
        System.out.println(max);
    }

    static class Point{
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static boolean[][] visited;

    static int[][] flood;

    private static void floodfill(int r, int c, int num) {
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(r, c));

        int count = 1;

        while (!que.isEmpty()) {
            Point poll = que.poll();

            for (int d = 0; d < 4; d++) {
                int nr = poll.r + dr[d];
                int nc = poll.c + dc[d];

                // 그냥 조건 다붙이는게 나은거같음
                // 범위안, 중복방문 x
                if (!isRange(nr,nc,len) || visited[nr][nc]) continue;

                // 음수 x
                if (map[nr][nc] <= 0) continue;

                // flood 0인곳만 가능
                if (flood[nr][nc] == 0){
                    visited[nr][nc] = true;
                    flood[nr][nc] = num;
                    que.add(new Point(nr, nc));
                    count++;
                }
            }
        }

        // floodfill 해서 해시맵에 저장했음.
        hashMap.put(num, count);
    }

    static Map<Integer, Integer> hashMap = new HashMap<>();

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    private static void melt() {
        int[][] copyMap = new int[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int iceCount = 0;

                // 음수거나 0임. -> 녹일필요없음
                if (map[i][j] <= 0) continue;

                for (int d = 0; d < 4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];

                    if (isRange(nr, nc, len)){
                        if (map[nr][nc] > 0) {
                            iceCount++;
                        }
                    }
                }

                // 3개 이상이면 minus x
                if (iceCount >= 3) {
                    continue;
                }
                else{ // 3개미만
                    copyMap[i][j]--;
                }
            }
        }

        // minus한것 통째로 반영(동시에?)
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                map[i][j] = copyMap[i][j];
            }
        }
    }

    private static boolean isRange(int nr, int nc, int N) {
        return 0 <= nr && nr <= N - 1 && 0 <= nc && nc <= N - 1;
    }

    private static void rotate90(int r, int c, int smallLen) {
        int[][] copyMap = new int[smallLen][smallLen];

        for (int i = 0; i < smallLen; i++) {
            for (int j = 0; j < smallLen; j++) {
                copyMap[j][smallLen - 1 - i] = map[i + r][j + c]; // 그냥 map 좌표만 평행이동
            }
        }

        /*
        // 기본 90도 회전
        for (int i = 0; i < smallLen; i++) {
            for (int j = 0; j < smallLen; j++) {
                copyMap[j][smallLen - 1 - i] = map[i][j];
            }
        }
        * */

        for (int i = 0; i < smallLen; i++) {
            for (int j = 0; j < smallLen; j++) {
                map[i + r][j + c] = copyMap[i][j];
            }
        }
    }

}