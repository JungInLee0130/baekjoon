import java.io.*;
import java.util.*;

public class Main {
    static int M, S;
    static int[][] smell;   // 냄새 turn 기록
    static int[][][] fishes;  // 세번째는 방향을 기록
    static int[][] result; //  방향 상관없이 담겨있는 물고기들
    static class Fish{
        int x;
        int y;

        int d;

        public Fish(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Fish : " + this.x + " " + this.y + " " + this.d;
        }
    }

    static class Shark {
        int x;
        int y;

        public Shark(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Shark : " + this.x + " " + this.y;
        }
    }

    static int[] ddx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] ddy = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        // 물고기 정보
        fishes = new int[4][4][8];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int fx = Integer.parseInt(st.nextToken()) - 1;
            int fy = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            fishes[fx][fy][d] += 1;
        }

        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken()) - 1;
        int sy = Integer.parseInt(st.nextToken()) - 1;

        shark = new Shark(sx, sy);

        turn = 1;

        smell = new int[4][4];

        while (turn <= S) {
            // 1. 복제 시전
            replicateStart();

            // 2. 물고기의 이동
            fishMove();     // 이동에 문제
            /*System.out.println("----------smell----------");    // 현재 지나간데 모두 smell 표시
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.print(smell[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("-------------------------");*/

            // 3. 상어의 이동
            getSharkDir();
            moveShark();

            // 4. 냄새제거 : 2번전 연습에서 생긴 물고기 냄새 제거
            removeSmell();

            // 5. 복제성공
            replicateComplete();

            // 턴 + 1
            turn += 1;
        }

        // 모든 물고기의 수는?
        int total = getFishCount();
        //print();

        System.out.println(total);


        br.close();
    }

    private static int getFishCount() {
        result = new int[4][4];
        int count = 0;

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                for (int d = 0; d < 8; d++) {
                    result[r][c] += fishes[r][c][d];
                }
                count += result[r][c];
            }
        }

        return count;
    }

    private static void fishMove() {
        int[][][] copyFishes = new int[4][4][8];

        for (int d = 0; d < 8; d++) {
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    if (fishes[r][c][d] > 0) {
                        Fish fish = new Fish(r, c, d);
                        int fishDir = getFishDir(fish);     // 이동방향을 정하는데 문제

                        int x = r;
                        int y = c;

                        // 이동 가능하면
                        if (fishDir >= 0) {
                            // move
                            int nx = x + ddx[fishDir];
                            int ny = y + ddy[fishDir];

                            // 새로운 좌표로 이동
                            copyFishes[nx][ny][fishDir] += fishes[x][y][d];
                        }
                        // 이동불가능한 경우 : 그대로
                        else {
                            copyFishes[x][y][d] += fishes[x][y][d];
                        }
                    }
                }
            }
        }

        // 복사
        fishes = copyFishes;
    }

    private static int getFishDir(Fish fish) {
        int d = fish.d;

        boolean isPossible = false;
        for (int times = 0; times < 8; times++) {
            isPossible = canMove(fish, d);

            // 이동가능한 방향 찾음.
            if(isPossible) break;

            // 방향 반시계 변경
            d = d - 1;
            if (d < 0) d = 7;
        }

        if (isPossible) {
            return d;
        }

        return -1;
    }

    private static void replicateStart() {
        copyFishes = new int[4][4][8];

        for (int d = 0; d < 8; d++) {
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    if (fishes[r][c][d] > 0) {
                        copyFishes[r][c][d] = fishes[r][c][d];
                    }
                }
            }
        }
    }

    static int[][][] copyFishes;

    private static void replicateComplete() {
        for (int d = 0; d < 8; d++) {
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    if (copyFishes[r][c][d] > 0) {
                        fishes[r][c][d] += copyFishes[r][c][d];
                    }
                }
            }
        }
    }

    private static void removeSmell() {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (smell[r][c] + 2 == turn) {
                    smell[r][c] = 0;
                }
            }
        }
    }

    private static void moveShark() {
        // 시작
        int sx = shark.x;
        int sy = shark.y;

        for (int t = 0; t < 3; t++) {
            // 방향
            int d = maxDir.charAt(t) - '0';

            // 움직이려는 좌표
            int mx = sx + dx[d];
            int my = sy + dy[d];

            // 물고기 다 먹고
            boolean isEaten = false;
            for (int i = 0; i < 8; i++) {
                if (fishes[mx][my][i] >= 1) {
                    isEaten = true;
                    fishes[mx][my][i] = 0;
                }
            }

            // 먹은곳이면
            if (isEaten) {
                // 냄새남김
                smell[mx][my] = turn;
            }

            // 좌표이동
            sx = mx;
            sy = my;
        }

        // 전역변수 상어 shark update
        shark.x = sx;
        shark.y = sy;
    }

    private static int fishDirToSharkDir(int fishDir) {
        if (fishDir == 2) return 0;
        if (fishDir == 0) return 1;
        if (fishDir == 6) return 2;
        if (fishDir == 4) return 3;

        return -1;
    }

    private static int sharkDirToFishDir(int sharkDir) {
        // 상 좌 하 우
        // 2 0 6 4
        // 0 1 2 3
        if (sharkDir == 0) return 2;
        if (sharkDir == 1) return 0;
        if (sharkDir == 2) return 6;
        if (sharkDir == 3) return 4;

        return -1;
    }

    static int turn;

    // 시뮬 -> dfs
    private static String getSharkDir() {
        int x = shark.x;
        int y = shark.y;

        int count = 0;
        maxFishCount = -1;
        maxDir = "";
        dfs(x, y, maxDir, 0, count);


        return maxDir;
    }

    static String maxDir;
    static int maxFishCount;

    private static void dfs(int x, int y, String dir, int depth, int fishCount) {
        if (depth == 3) {
            // maxCount 비교
            if (maxFishCount < fishCount) {
                maxFishCount = fishCount;
                maxDir = dir;
            }
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 격자밖일경우
            if (isOut(nx, ny)) continue;

            int[] fishCounts = new int[8];
            int count = 0;
            for (int i = 0; i < 8; i++) {
                if (fishes[nx][ny][i] > 0) {
                    fishCounts[i] = fishes[nx][ny][i];
                    fishes[nx][ny][i] = 0;
                    count += fishCounts[i];
                }
            }

            dfs(nx, ny, dir + d, depth + 1, fishCount + count);

            for (int i = 0; i < 8; i++) {
                fishes[nx][ny][i] = fishCounts[i];
            }
        }
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    private static boolean canMove(Fish fish, int dir) {
        int x = fish.x;
        int y = fish.y;
        int d = dir;

        int nx = x + ddx[d];
        int ny = y + ddy[d];

        // 벽자 밖이면
        if (isOut(nx,ny)) return false;
        // 상어가 있거나
        if (nx == shark.x && ny == shark.y) return false;
        // 냄새가 있거나
        if (smell[nx][ny] >= 1) return false;

        return true;
    }

    private static boolean isOut(int nx, int ny) {
        return 0 > nx || nx > 3 || 0 > ny || ny > 3;
    }

    static Shark shark;


    private static void print() {
        int N = 4;
        System.out.println("---------map-----------");
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                System.out.print(result[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }
}