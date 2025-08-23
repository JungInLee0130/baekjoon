import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {
    static int N, M;
    static int[] result;
    static int[][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        result = new int[3];

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 입력할때는 4개이상의 연속하는 구슬이없음.
        // 상어가 있는 칸도 0, 구슬이 없으면 0
        // 상어의 위치는 항상 : ((N+1)/2, (N+1)/2)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            // 1. 블리자드
            blizard(d,s);
            // 2. 이동 : 한번에 한번의 0만 없애므로 t번 반복
            for (int t = 0; t < s; t++) {
                move();    
            }
            // 3. 폭발
            // 연속하는 4개의 같은 구슬이 없을때까지 2-3 반복
            while (true) {
                bumb();
                if (bumbCount == 0) {
                    break;
                }
                // bumbCount == 0 일때 까지 폭발 -> 이동 반복
                while (bumbCount-- > 0) {
                    move();
                }
            }

            // 4. 변화, 넣기
            change();
            put();

            //print();
        }

        System.out.println(1 * result[0]
                + 2 * result[1]
                + 3 * result[2]);

        br.close();
    }

    private static void put() {
        int[][] copyMap = new int[N][N];

        int curX = N / 2;
        int curY = N / 2;

        int sx = curX;
        int sy = curY;

        int dir = 0;

        int mx = sx;
        int my = sy;

        int stepCount = 0;
        int maxStepCount = 1;
        int rotateCount = 0;

        while (!(sx == 0 && sy == 0) && !marbles.isEmpty()) {
            mx = sx + ddx[dir];
            my = sy + ddy[dir];

            copyMap[mx][my] = marbles.poll();

            stepCount += 1;

            if (stepCount == maxStepCount) {
                stepCount = 0;
                rotateCount += 1;
                dir = (dir + 1) % 4;
            }

            if (rotateCount == 2) {
                rotateCount = 0;
                maxStepCount += 1;
            }

            sx = mx;
            sy = my;
        }

        map = copyMap;
    }

    // 그냥 기존 map -> 순회하면서 새로운거 다 arrayList에 넣고
    // 새로운거 map 순회하면서 arrayList 빼서 넣으면 될듯?
    
    static Queue<Integer> marbles;
    private static void change() {
        // 새로운 구슬 넣을 배열
        marbles = new LinkedList<>();
        
        int curX = N / 2;
        int curY = N / 2;

        int sx = curX;
        int sy = curY;

        int dir = 0;

        int mx = sx + ddx[dir];
        int my = sy + ddy[dir];

        int stepCount = 0;
        int maxStepCount = 1;
        int rotateCount = 0;

        stepCount += 1;

        // 방향회전
        if (stepCount == maxStepCount) {
            stepCount = 0;
            rotateCount += 1;
            dir = (dir + 1) % 4;
        }

        if (rotateCount == 2) {
            rotateCount = 0;
            maxStepCount += 1;
        }

        sx = mx;
        sy = my;

        int A = 1; // == sequenceCount
        int B = map[mx][my];

        while (!(sx == 0 && sy == 0 )) {
            mx = sx + ddx[dir];
            my = sy + ddy[dir];

            // 숫자가 같으면
            if (map[sx][sy] == map[mx][my]) {
                A += 1;     // 개수 + 1
            }
            // 숫자가 다름 : 새로운 배열에 A, B 넣기
            else {
                marbles.add(A);
                marbles.add(B);

                A = 1;  // 개수 초기화
                B = map[mx][my];    // B 초기화
            }

            stepCount += 1;

            // 방향회전
            if (stepCount == maxStepCount) {
                stepCount = 0;
                rotateCount += 1;
                dir = (dir + 1) % 4;
            }

            if (rotateCount == 2) {
                rotateCount = 0;
                maxStepCount += 1;
            }

            sx = mx;
            sy = my;
        }
    }

    static int bumbCount = 0;
    static boolean bumbFinished;
    private static void bumb() {
        bumbFinished = false;

        int curX = N / 2;
        int curY = N / 2;
        
        int sx = curX;
        int sy = curY;
        
        int dir = 0;

        // 이동좌표
        int mx = sx + ddx[dir];
        int my = sy + ddy[dir];
        
        int ex = 0;
        int ey = 0;
        
        // 부가 변수
        int stepCount = 0;
        int maxStepCount = 1;
        int rotateCount = 0;

        stepCount += 1;

        if (stepCount == maxStepCount) {
            stepCount = 0;
            rotateCount += 1;
            dir = (dir + 1) % 4;    // 방향 교체
        }

        if (rotateCount == 2) {
            rotateCount = 0;
            maxStepCount += 1;      // 최대 걸음수 + 1
        }

        sx = mx;
        sy = my;

        // 연속된 숫자 count해서 4개 이상이면 모두 0
        // 좌표는 arraylist에 저장
        List<Point> sequence = new ArrayList<>();
        bumbCount = 0;
        int sequenceCount = 1;

        while (!(ex == sx && ey == sy)) {
            mx = sx + ddx[dir];
            my = sy + ddy[dir];
            
            // 구슬 숫자가 같으면 && 0이 아니고
            if (map[mx][my] != 0 
                    && map[sx][sy] == map[mx][my]) {
                if (sequenceCount == 1) {
                    sequence.add(new Point(sx, sy));
                }
                sequenceCount += 1;
                sequence.add(new Point(mx, my));
            }
            // 연속되지않을때
            else {
                if (sequenceCount >= 4) {
                    int idx = map[sx][sy] - 1;   // idx
                    for (Point point : sequence) {
                        map[point.x][point.y] = 0;  // 모두 폭발
                    }
                    bumbCount += sequenceCount;     // 터진폭탄수 + sequenceCount
                    result[idx] += sequenceCount;    // result : 터진폭탄 number + sequenceCount
                }
                sequenceCount = 1;
                sequence = new ArrayList<>(); // 초기화
            }

            stepCount += 1;

            if (stepCount == maxStepCount) {
                stepCount = 0;
                rotateCount += 1;
                dir = (dir + 1) % 4;
            }

            if (rotateCount == 2) {
                rotateCount = 0;
                maxStepCount += 1;
            }

            sx = mx;
            sy = my;
        }

        if (bumbCount == 0) {
            bumbFinished = true;
        }
    }
    

    private static void print() {
        System.out.println("---------map-----------");
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }

    // 회전용
    static int[] ddx = {0, 1, 0, -1};
    static int[] ddy = {-1, 0, 1, 0};
    private static void move() {
        int curX = N / 2;
        int curY = N / 2;

        // 시작 : 이전 좌표 대신할수있음
        int sx = curX;
        int sy = curY;

        // 끝 : 0,0
        int ex = 0;
        int ey = 0;

        // 종점에 닿을때까지 반복
        int dir = 0;    // 방향 : ddx, ddy
        int stepCount = 0;  // step
        int maxStepCount = 1;   // 최대 step

        int mx = sx + ddx[dir];
        int my = sy + ddy[dir];

        int rotateCount = 0;    // 회전 2번 -> maxStep++

        stepCount += 1;

        // 방향전환
        if (stepCount == maxStepCount) {
            stepCount = 0;
            rotateCount += 1;
            dir = (dir + 1) % 4;
        }

        sx = mx;
        sy = my;

        while (!(ex == sx && ey == sy)) {
            mx = sx + ddx[dir];
            my = sy + ddy[dir];

            // 빈칸이면 : 다음칸에서 땡겨옴
            // 이전칸 저장
            if (map[sx][sy] == 0) {
                map[sx][sy] = map[mx][my];
                map[mx][my] = 0;
            }

            stepCount += 1; // 움직임

            // 방향전환
            if (stepCount == maxStepCount) {
                stepCount = 0;
                rotateCount += 1;
                dir = (dir + 1) % 4;
            }

            // 회전 2번하면 step += 1;
            if (rotateCount == 2) {
                rotateCount = 0;
                maxStepCount += 1;
            }

            sx = mx;
            sy = my;
        }

    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    private static void blizard(int d, int s) {
        // 상어 좌표

        int curX = N / 2;   // N은 항상 홀수
        int curY = N / 2;

        for (int i = 0; i < s; i++) { // s반복
            curX += dx[d];  // 방향만큼 이동
            curY += dy[d];

            map[curX][curY] = 0;
        }
    }
}