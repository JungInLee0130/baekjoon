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

            while (r < len){
                c = 0;
                while (c < len) {
                    // 회전하기
                    rotate90(r, c, smallLen);
                    c += smallLen;
                }
                r += smallLen;
            }

            melt(0, 0, len);
        }

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
        visited[r][c] = true;

        int count = 1;
        while (!que.isEmpty()) {
            Point poll = que.poll();

            for (int d = 0; d < 4; d++) {
                int nr = poll.r + dr[d];
                int nc = poll.c + dc[d];

                if (!isRange(nr,nc,len)
                        || visited[nr][nc]) continue;

                if (map[nr][nc] <= 0) continue;
                
                if (flood[nr][nc] == 0){
                    visited[nr][nc] = true;
                    flood[nr][nc] = num;
                    que.add(new Point(nr, nc));
                    count++;
                }
            }
        }

        hashMap.put(num, count);
    }

    static Map<Integer, Integer> hashMap = new HashMap<>();

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    private static void melt(int r, int c, int smallLen) {
        /*int iceCount = 0;

        for (int j = c; j < c + smallLen; j++) {
            iceCount += meltingParticle(r, j, smallLen);
        }

        for (int i = r; i < r + smallLen; i++) {
            iceCount += meltingParticle(i, c, smallLen);
        }*/

        int[][] copyMap = new int[smallLen][smallLen];

        for (int i = r; i < r + smallLen; i++) {
            for (int j = c; j < c + smallLen; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        for (int i = r; i < r + smallLen; i++) {
            for (int j = c; j < c + smallLen; j++) {
                int iceCount = 0;

                if (map[i][j] < 0) continue;

                for (int d = 0; d < 4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];

                    if (isRange(nr, nc, smallLen)){
                        if (map[nr][nc] > 0) {
                            iceCount++;
                        }
                    }
                }

                if (iceCount >= 3) {
                    continue;
                }
                else{ // 3개미만
                    copyMap[i][j]--;
                }
            }
        }

        for (int i = r; i < r + smallLen; i++) {
            for (int j = c; j < c + smallLen; j++) {
                map[i][j] = copyMap[i][j];
            }
        }
    }

    /*private static int meltingParticle(int i, int j, int smallLen) {
        if (map[i][j] < 0) return 0;

        for (int d = 0; d < 4; d++) {
            int nr = i + dr[d];
            int nc = j + dc[d];

            if (isRange(nr, nc, smallLen)){
                if (map[nr][nc] > 0) {
                    iceCount++;
                }
            }
        }

        if (iceCount >= 3) {
            continue;
        }
        else{ // 3개미만
            map[i][j]--;
        }
    }*/

    private static boolean isRange(int nr, int nc, int N) {
        return 0 <= nr && nr <= N - 1 && 0 <= nc && nc <= N - 1;
    }

    private static void rotate90(int r, int c, int smallLen) {
        int[][] copyMap = new int[smallLen][smallLen];

        for (int i = 0; i < smallLen; i++) {
            for (int j = 0; j < smallLen; j++) {
                copyMap[j][smallLen - 1 - i] = map[i + r][j + c];
            }
        }

        /*
        for (int i = 0; i < smallLen; i++) {
            for (int j = 0; j < smallLen; j++) {
                copyMap[j][smallLen - 1 - i] = map[i + r][j + c];
            }
        }
        * */

        for (int i = 0; i < smallLen; i++) {
            for (int j = 0; j < smallLen; j++) {
                map[i + r][j + c] = copyMap[i][j];
            }
        }

        // 이거 확인해봐야할듯
    }

}