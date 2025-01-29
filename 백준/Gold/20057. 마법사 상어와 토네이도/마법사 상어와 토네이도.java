import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // N / 2 -> 중간
        int total = 0;
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                total += map[i][j];
            }
        }

        // 모래 양은 더함.
        // 중간부터 시작
        // 0,0까지 가고, 범위 밖으로 나가는 모래양 출력
        int sx = N / 2;
        int sy = N / 2;
        int d = 0;
        int cnt = 0;
        int mul = 1;
        boolean isOut = false;

        while (!(sx == 0 && sy == 0)){
            for (int i = 0; i < mul; i++) {
                int mx = sx + dx[d];
                int my = sy + dy[d];

                if (!isRange(mx, my)) {
                    isOut = true;
                    break;
                }

                // 바깥으로 나갈일은 없음.
                if (map[mx][my] > 0) {
                    int moveSand = map[mx][my];

                    int leftSand = moveSand;
                    leftSand -= move2(mx, my, moveSand, d);
                    leftSand -= move3(mx, my, moveSand, d);
                    leftSand -= move4(mx, my, moveSand, d);
                    leftSand -= move5(mx, my, moveSand, d);
                    leftSand -= move6(mx, my, moveSand, d);

                    move1(mx, my, d, leftSand);

                    map[mx][my] = 0;
                }

                sx = mx;
                sy = my;
            }

            if (isOut) break;


            d = (d + 1) % 4; // 0 ~ 3
            cnt++;

            if (cnt % 2 == 0) {
                mul++;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //System.out.print(map[i][j] + " ");
                total -= map[i][j];
            }
            //System.out.println();
        }

        System.out.println(total);

        br.close();
    }

    private static int move6(int mx, int my, int moveSand, int d) {
        int sum = 0;
        int sand = (int) (moveSand * 0.05);

        mx += dx[d] * 2;
        my += dy[d] * 2;

        if (isRange(mx, my)) {
            map[mx][my] += sand;
            sum += sand;
        }

        return sand;
    }

    private static int move5(int mx, int my, int moveSand, int d) {
        int sand = (int) (moveSand * 0.02);

        if (d == 0 || d == 2) {
            if (isRange(mx + 2, my)){
                map[mx + 2][my] += sand;
            }
            if (isRange(mx - 2, my)) {
                map[mx - 2][my] += sand;
            }
        }
        if (d == 1 || d == 3){
            if (isRange(mx, my + 2)) {
                map[mx][my + 2] += sand;
            }
            if (isRange(mx, my - 2)){
                map[mx][my - 2] += sand;
            }
        }
        return 2 * sand;
    }

    private static int move4(int mx, int my, int moveSand, int d) {
        int sand = (int) (moveSand * 0.01);

        if (d == 0 || d == 2){
            if (isRange(mx - 1, my - dy[d])){
                map[mx - 1][my - dy[d]] += sand;
            }
            if (isRange(mx + 1, my - dy[d])) {
                map[mx + 1][my - dy[d]] += sand;
            }
        }
        if (d == 1 || d == 3){
            if(isRange(mx - dx[d], my - 1)){
                map[mx - dx[d]][my - 1] += sand;
            }
            if (isRange(mx - dx[d], my + 1)) {
                map[mx - dx[d]][my + 1] += sand;
            }
        }
        return 2 * sand;
    }

    private static int move3(int mx, int my, int moveSand, int d) {
        int sand = (int) (moveSand * 0.07);

        if (d == 0 || d == 2){
            if (isRange(mx - 1, my)){
                map[mx - 1][my] += sand;
            }
            if (isRange(mx + 1, my)) {
                map[mx + 1][my] += sand;
            }
        }
        if (d == 1 || d == 3){
            if (isRange(mx, my - 1)){
                map[mx][my - 1] += sand;
            }
            if (isRange(mx, my + 1)) {
                map[mx][my + 1] += sand;
            }
        }
        return 2 * sand;
    }

    private static int move2(int mx, int my, int moveSand, int d) {
        int sand = (int) (moveSand * 0.1);

        if (d == 0 || d == 2) { // 왼쪽
            if (isRange(mx - 1, my + dy[d])) {
                map[mx - 1][my + dy[d]] += sand;
            }
            if (isRange(mx + 1, my + dy[d])){
                map[mx + 1][my + dy[d]] += sand;
            }
        }
        if (d == 1 || d == 3){ // 아래 // 위
            if (isRange(mx + dx[d], my - 1)){
                map[mx + dx[d]][my - 1] += sand;
            }

            if (isRange(mx + dx[d], my + 1)) {
                map[mx + dx[d]][my + 1] += sand;
            }
        }
        return 2 * sand;
    }

    private static void move1(int mx, int my, int d, int leftSand) {
        mx += dx[d];
        my += dy[d];

        if (isRange(mx,my)){
            map[mx][my] += leftSand;
        }
    }

    private static boolean isRange(int mx, int my) {
        return 0 <= mx && mx <= N - 1 && 0 <= my && my <= N - 1;
    }

    static int outSand = 0;

    // 왼 아 오 위
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
}