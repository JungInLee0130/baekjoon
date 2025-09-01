import java.io.*;
import java.util.*;

public class Main {

    static int[] dx4 = {-1, 1, 0, 0};
    static int[] dy4 = {0, 0, -1, 1};

    static int N, M, K;
    static int[][] map; // 상어번호만 적혀있는 map
    static class Shark {
        int num;
        int x;
        int y;
        int d;

        boolean isAlive;

        public Shark(int num, int x, int y, int d, boolean isAlive) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.d = d;
            this.isAlive = true;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "num=" + num +
                    ", x=" + x +
                    ", y=" + y +
                    ", d=" + d +
                    ", isAlive=" + isAlive +
                    '}';
        }
    }
    static Shark[] sharks;
    static int[][][] priorities;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        initFirst();

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());

                int num = map[r][c];
                Shark shark = new Shark(num, r, c, 0, true);
                sharks[num] = shark;

                sharkMap[r][c].add(num);
            }
        }

        st = new StringTokenizer(br.readLine());

        // 상어 M마리 방향
        for (int i = 1; i <= M; i++) {
            sharks[i].d = Integer.parseInt(st.nextToken()) - 1; // 방향
        }

        // M개의 shark의 우선순위
        priorities = new int[M + 1][4][4];
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    priorities[i][j][k] = Integer.parseInt(st.nextToken()) - 1;    // 방향
                }
            }
        }

        // 1. 상어의 이동, 냄새 남기기
        // 2. 같은격자에있는 상어 모두 삭제하기
        // 3. 1번 상어만 남았는지 점검하기
        // 4. 냄새들 time -1 하기

        while (time <= 1000) {
            time++;
            //printShark();
            //System.out.println(time);
            //printMap(time);

            leavingSmell();

            moveShark();

            //printMap(time);
            //printSmell(time);

            deleteShark();

            // 1번상어만 남아있을경우
            if (checkLeftShark()) {
                break;
            }

            decreaseAllSmellLeftTime();

            //printSmell(time);

            //printShark();
        }

        if (time >= 1001) {
            System.out.println(-1);
        }
        else {
            System.out.println(time);
        }


        br.close();
    }

    private static void leavingSmell() {
        for (int i = 1; i <= M; i++) {
            Shark shark = sharks[i];

            int num = shark.num;
            int x = shark.x;
            int y = shark.y;

            if (shark.isAlive) {
                // 먼저 냄새남김
                Smell nSmell = new Smell(num, x, y, K);    // K시간

                // 냄새추가
                smells[x][y] = nSmell;
            }
        }
    }

    private static void printSmell(int t) {
        System.out.println("----------------" + t + ": Smell----------------");
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (smells[r][c] != null) {
                    System.out.print(smells[r][c].num + " ");
                }
                else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
        System.out.println("----------------------------------------");
    }
    private static void printMap(int t) {
        String[][] result = new String[N][N];
        System.out.println("----------------" + t + ": sharkMap----------------");
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                result[r][c] = "";

                if (sharkMap[r][c].size() >= 2) {
                    for (Integer num : sharkMap[r][c]) {
                        result[r][c] += num;
                    }
                    System.out.print(result[r][c] + " ");
                } else if (sharkMap[r][c].size() == 1){
                    result[r][c] += sharkMap[r][c].get(0);
                    System.out.print(result[r][c] + " ");
                } else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
        System.out.println("----------------------------------------");
    }

    private static void printShark() {
        for (int i = 1; i <= M; i++) {
            System.out.println(sharks[i].toString());
        }
    }

    private static void initFirst() {
        map = new int[N][N];
        sharks = new Shark[M + 1];
        sharkMap = new ArrayList[N][N];
        initSharkMap(sharkMap);
        smells = new Smell[N][N];
    }

    private static void decreaseAllSmellLeftTime() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (smells[r][c] != null) {
                    Smell smell = smells[r][c];

                    int curNum = smell.num;
                    int curTime = smell.time;

                    curTime -= 1;

                    // 냄새 제거
                    if (curTime == 0) {
                        smells[r][c] = null;
                    }
                    else {
                        smells[r][c] = new Smell(curNum, r, c, curTime);
                    }
                }
            }
        }
    }

    static int time = 0;
    private static boolean checkLeftShark() {
        for (int i = 2; i <= M; i++) {
            Shark shark = sharks[i];
            // 2번 shark 이후로 한마리라도 살아있으면 false
            if (shark.isAlive) {
                return false;
            }
        }
        return true;
    }

    private static void deleteShark() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (sharkMap[r][c].size() >= 2) {
                    // 가장 작은 숫자의 shark 찾기
                    int minNum = Integer.MAX_VALUE;
                    for (Integer num: sharkMap[r][c]) {
                        minNum = Math.min(minNum, num);
                    }

                    // sharks 수정
                    for (Integer num : sharkMap[r][c]) {
                        // 최소 숫자의 shark만 isAlive = true
                        if (num > minNum) {
                            sharks[num].isAlive = false;
                        }
                    }

                    // List<Integer>에서 제거
                    int size = sharkMap[r][c].size();
                    for (int i = size - 1; i >= 0; i--) {
                        int num = sharkMap[r][c].get(i);
                        if (minNum != num) {
                            sharkMap[r][c].remove(i);
                        }
                    }
                }
            }
        }
    }

    private static void moveShark() {
        List<Integer>[][] nSharkMap = new ArrayList[N][N];

        initSharkMap(nSharkMap);

        for (int i = 1; i <= M; i++) {
            Shark shark = sharks[i];

            // 살아있는 상어만
            if (shark.isAlive) {
                int curNum = shark.num;
                int x = shark.x;
                int y = shark.y;
                int d = shark.d;

                // 이동가능한지
                int dir = getDir(shark);

                // 이동가능함
                if (dir >= 0) {
                    int nx = x + dx4[dir];
                    int ny = y + dy4[dir];

                    // 상어의 정보 업데이트 : 새로운 dir로 업데이트
                    Shark nShark = new Shark(curNum, nx, ny, dir, true);

                    // 상어배열 업데이트
                    sharks[i] = nShark;
                    nSharkMap[nx][ny].add(curNum);
                }
            }
        }
        sharkMap = nSharkMap;
    }

    private static void initSharkMap(List<Integer>[][] sharkMap) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                sharkMap[r][c] = new ArrayList<>();
            }
        }
    }

    static class Smell {
        int num;
        int x;
        int y;
        int time;

        public Smell(int num, int x, int y, int time) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static Smell[][] smells;
    static List<Integer>[][] sharkMap;
    private static int getDir(Shark shark) {
        int num = shark.num;
        int x = shark.x;
        int y = shark.y;
        int d = shark.d;

        int[] dirs = priorities[num][d];

        // 1. 냄새가 없음
        for (int i = 0; i < 4; i++) {
            int curD = dirs[i];
            int nx = x + dx4[curD];
            int ny = y + dy4[curD];

            if (isOut(nx,ny)) continue;

            if (smells[nx][ny] == null) {
                return curD;
            }
        }

        // 2. 자신의 냄새가있음
        for (int i = 0; i < 4; i++) {
            int curD = dirs[i];
            int nx = x + dx4[curD];
            int ny = y + dy4[curD];

            if (isOut(nx,ny)) continue;

            if (smells[nx][ny].num == num) {
                return curD;
            }
        }

        return -1;
    }

    private static boolean isOut(int nx, int ny) {
        return !(0 <= nx && nx <= N - 1 && 0 <= ny && ny <= N - 1);
    }

    private static void print(int[][] map) {
        int N = 4;
        System.out.println("---------map-----------");
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }
}