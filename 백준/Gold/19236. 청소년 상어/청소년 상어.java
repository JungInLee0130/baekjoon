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

        for (int r = 0; r < 4; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int c = 0; c < 4; c++) {
                int num = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken()) - 1;

                Fish fish = new Fish(num, r, c, d);

                fishes[r][c] = fish;
            }
        }

        count = 0;

        // 1. 청소년 상어 투입
        Fish fish = fishes[0][0];
        shark = new Shark(0,0, fish.d);
        fishes[0][0] = null;
        count += fish.num;

        // 근본적으로 2,3 부터 dfs를 반복
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

        int nx = x;
        int ny = y;

        int nd = getDir(curFish);

        if (nd >= 0) {
            nx = x + dx8[nd];
            ny = y + dy8[nd];

            Fish fish = new Fish(num, nx, ny, nd);

            if (fishes[nx][ny] == null) {
                fishes[nx][ny] = fish;
                fishes[x][y] = null;
            } else {
                Fish exchangeFish = new Fish(fishes[nx][ny].num, x, y, fishes[nx][ny].d);
                fishes[nx][ny] = fish;
                fishes[x][y] = exchangeFish;
            }
        }
    }

    static int count;

    static int maxCount = 0;

    private static void dfs() {
        // 2. 물고기의 이동
        Fish[][] prevFishes = copyFish();   // 이동전 사진을 찍음
        for (int k = 1; k <= 16; k++) {     // 예제 3,4 틀린것 : 범위 실수
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

        // 이동을 못함 : maxCount 비교
        maxCount = Math.max(maxCount, count);

        fishes = prevFishes;        // 이동전으로 복귀
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

            if (isOut(nx,ny)
                    || (shark.x == nx && shark.y == ny)) {
                d = (d + 1) % 8;
            }
            else {
                return d;
            }
        }

        return -1;
    }

    private static boolean isOut(int nx, int ny) {
        return 0 > nx || nx > 3 || 0 > ny || ny > 3;        // 여기서 범위 실수
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