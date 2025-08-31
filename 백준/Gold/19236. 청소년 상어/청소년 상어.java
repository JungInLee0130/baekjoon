import java.io.*;
import java.util.*;

public class Main {
    static Fish[][] fishes;  // 세번째는 방향을 기록
    static class Fish{
        int num;
        int x;
        int y;
        int d;

        public Fish(int num, int x, int y, int d) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static class Shark{
        int x;
        int y;
        int d;

        public Shark(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static Shark shark;

    static int[] dx8 = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy8 = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        fishes = new Fish[4][4];
        // 번호 순서로 오름차순
        for (int r = 0; r < 4; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int c = 0; c < 4; c++) {
                int num = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken()) - 1;

                // r,c num, d
                Fish fish = new Fish(num, r, c, d);

                fishes[r][c] = fish;    // fish 위치들 기록
            }
        }

        count = 0;

        // 1. 청소년 상어 투입
        // (0,0)에 투입
        // (0,0) 물고기를 먹고, 그 방향을 가짐
        Fish fish = fishes[0][0];
        shark = new Shark(0,0, fish.d);
        fishes[0][0] = null;
        count += fish.num;

        dfs();

        System.out.println(maxCount);

        br.close();
    }

    private static Fish getFish(int num) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (fishes[i][j] == null) continue;
                if (fishes[i][j].num == num) {
                    return fishes[i][j];
                }
            }
        }
        return null;
    }

    private static void moveFish(Fish curFish) {
        int num = curFish.num;
        int x = curFish.x;
        int y = curFish.y;
        int d = curFish.d;

        int nx = x;
        int ny = y;

        // 이동할수있으면 > 0 방향 반환
        int nd = getDir(curFish);

        // 이동할수있으면 : 이동
        // 빈칸 -> 그냥 이동
        // 물고기있으면 -> 위치 교환
        if (nd > -1) {
            nx = x + dx8[nd];
            ny = y + dy8[nd];

            Fish fish = new Fish(num, nx, ny, nd);

            // 물고기가 없음 -> 그냥 이동
            if (fishes[nx][ny] == null) {
                fishes[nx][ny] = fish;
                fishes[x][y] = null;
            }
            // 물고기가 있음 -> 교환
            else{
                Fish prevFish = new Fish(fishes[nx][ny].num, x, y, fishes[nx][ny].d);
                fishes[nx][ny] = fish;
                fishes[x][y] = prevFish;
            }
            // updateFish에 대입

        }
        // 이동할수없으면 그냥 정지
        else {

        }
    }

    static int count;

    static int maxCount = 0;

    private static void dfs() {
        // 2. 물고기의 이동
        Fish[][] prevFishes = copyFish();
        for (int k = 1; k <= 16; k++) {
            Fish fish = getFish(k);
            if (fish != null) {
                moveFish(fish);
            }
        }
        // 3. 상어의 이동 : 브루트포스 완전탐색
        int sharkX = shark.x;
        int sharkY = shark.y;
        int sharkDir = shark.d;

        int nx = sharkX;
        int ny = sharkY;

        for (int t = 1; t <= 3; t++) {
            // 경계 밖 x, 물고기가 없으면
            if (isOut(sharkX + dx8[sharkDir] * t,sharkY + dy8[sharkDir] * t)) continue;
            if (fishes[sharkX + dx8[sharkDir] * t][sharkY + dy8[sharkDir] * t] == null) continue;

            nx = sharkX + dx8[sharkDir] * t;
            ny = sharkY + dy8[sharkDir] * t;

            // 물고기 잡아먹기 가능
            count += fishes[nx][ny].num;
            shark = new Shark(nx, ny, fishes[nx][ny].d);
            Fish eatenFish = fishes[nx][ny];
            fishes[nx][ny] = null;

            dfs();

            fishes[nx][ny] = eatenFish;
            count -= fishes[nx][ny].num;
            shark = new Shark(sharkX, sharkY, sharkDir);
        }

        if (maxCount < count) {
            maxCount = count;
        }

        fishes = prevFishes;
    }

    private static Fish[][] copyFish() {
        Fish[][] copyFishes = new Fish[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copyFishes[i][j] = fishes[i][j];
            }
        }
        return copyFishes;
    }

    private static int getDir(Fish curFish) {
        int r = curFish.x;
        int c = curFish.y;
        int d = curFish.d;

        for (int times = 0; times < 8; times++) {
            int nx = r + dx8[d];
            int ny = c + dy8[d];

            // 경계밖이거나 상어가있다면 : 반시계 회전
            if (isOut(nx,ny)
                    || (shark.x == nx && shark.y == ny)) {
                d = (d + 1) % 8;    // 0 ~ 7 사이로 회전
            }
            // 이동 가능하면
            else {
                return d;
            }
        }

        return -1;
    }

    private static boolean isOut(int nx, int ny) {
        return 0 > nx || nx > 3 || 0 > ny || ny > 3;
    }

    private static void print() {
        int N = 4;
        System.out.println("---------map-----------");
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (fishes[r][c] == null) {
                    System.out.print("null ");
                    continue;
                }
                System.out.print(fishes[r][c].num + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }
}