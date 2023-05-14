import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static ArrayList<Point> numbers;
    static class Point{
        int x;
        int y;
        int num;

        public Point(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        numbers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 수가 들어있는 좌표저장. -> 배열을 직접 움직이는게 아닌 좌표 위치만 바꿔준다.
                if (map[i][j] > 0) {
                    numbers.add(new Point(i, j, map[i][j]));
                }
            }
        }

        // 2의 제곱수로 주어짐. 5번안에 만들수있는 가장 큰 블록값 리턴.
        dfs(map, 0);

        System.out.println(result);

        bw.flush();
        br.close();
        bw.close();
    }
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static int result = Integer.MIN_VALUE;

    private static int[][][] copy;
    private static void dfs(int[][] map, int cnt) {
        if (cnt >= 5){
            if (cnt == 5){
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (result < map[i][j]){
                            result = map[i][j];
                        }
                    }
                }
                /*System.out.println(cnt + ":");
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        System.out.print(map[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println();*/
            }
            return;
        }


        // map은 변하니까 copy를 쓰자
        for (int d = 0; d < 4; d++) {
            int[][] copy = new int[N][N];
            for (int i = 0; i < N; i++) {
                // map은 단순 copy용
                copy[i] = Arrays.copyOf(map[i], N);
            }

            if (d == 0) {
                up(copy, d);
            } else if (d == 1) {
                down(copy, d);
            } else if (d == 2) {
                left(copy, d);
            } else if (d == 3) {
                right(copy, d);
            }

            dfs(copy,cnt + 1); // 다음 라운드
        }
    }

    // 상
    // 위에서 아래로 쭉훑는거 맞음.
    private static void up(int[][] copy, int d) {
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (copy[i][j] != 0){ // 숫자가 있는 것에 대해서
                    int curR = i;
                    int curC = j; // 현재 좌표

                    int newR = curR;
                    int newC = curC;

                    // direction만큼 이동
                    // 거꾸로해야할듯
                    // 위 -> 아래
                    // up, down, left, right 나눔
                    while (true){
                        newR = curR + dr[d];
                        newC = curC + dc[d];

                        // 범위 밖이면 break
                        // 앞에 자신과 다른수가있으면 break
                        if (!check(newR,newC)) break;
                        if (visited[newR][newC]) break;

                        // 0이면 계속이동
                        if (copy[newR][newC] == 0) {
                            copy[newR][newC] = copy[curR][curC];
                            copy[curR][curC] = 0;
                            curR = newR;
                            curC = newC;
                            continue;
                        }
                        // 자신과 같은수를 만남. -> 합침
                        if (copy[newR][newC] == copy[curR][curC]){
                            copy[newR][newC] *= 2;
                            copy[curR][curC] = 0;
                            visited[newR][newC] = true; // 합치고 그 라운드 visited
                            break;
                        }

                        if (copy[newR][newC] != copy[curR][curC]) break;
                        curR = newR;
                        curC = newC;
                    }
                }
            }
        }
    }
    private static void down(int[][] copy, int d) {
        boolean[][] visited = new boolean[N][N];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if (copy[i][j] != 0){ // 숫자가 있는 것에 대해서
                    int curR = i;
                    int curC = j; // 현재 좌표

                    int newR = curR;
                    int newC = curC;

                    // direction만큼 이동
                    // 거꾸로해야할듯
                    // 위 -> 아래
                    // up, down, left, right 나눔
                    while (true){
                        newR = curR + dr[d];
                        newC = curC + dc[d];

                        // 범위 밖이면 break
                        // 앞에 자신과 다른수가있으면 break
                        if (!check(newR,newC)) break;
                        if (visited[newR][newC]) break;

                        // 0이면 계속이동
                        if (copy[newR][newC] == 0) {
                            copy[newR][newC] = copy[curR][curC];
                            copy[curR][curC] = 0;
                            curR = newR;
                            curC = newC;
                            continue;
                        }

                        // 자신과 같은수를 만남. -> 합침
                        if (copy[newR][newC] == copy[curR][curC]){
                            copy[newR][newC] *= 2;
                            copy[curR][curC] = 0;
                            visited[newR][newC] = true; // 합치고 그 라운드 visited
                            break;
                        }

                        if (copy[newR][newC] != copy[curR][curC]) break;
                        curR = newR;
                        curC = newC;
                    }
                }
            }
        }
    }

    private static void left(int[][] copy, int d) {
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (copy[i][j] != 0) { // 숫자가 있는 것에 대해서
                    int curR = i;
                    int curC = j; // 현재 좌표

                    int newR = curR;
                    int newC = curC;

                    // direction만큼 이동
                    // 거꾸로해야할듯
                    // 위 -> 아래
                    // up, down, left, right 나눔
                    while (true){
                        newR = curR + dr[d];
                        newC = curC + dc[d];

                        // 범위 밖이면 break
                        // 앞에 자신과 다른수가있으면 break
                        if (!check(newR,newC)) break;
                        if (visited[newR][newC]) break;

                        // 0이면 계속이동
                        if (copy[newR][newC] == 0) {
                            copy[newR][newC] = copy[curR][curC];
                            copy[curR][curC] = 0;
                            curR = newR;
                            curC = newC;
                            continue;
                        }

                        // 자신과 같은수를 만남. -> 합침
                        if (copy[newR][newC] == copy[curR][curC]){
                            copy[newR][newC] *= 2;
                            copy[curR][curC] = 0;
                            visited[newR][newC] = true; // 합치고 그 라운드 visited
                            break;
                        }

                        if (copy[newR][newC] != copy[curR][curC]) break;
                        curR = newR;
                        curC = newC;
                    }
                }
            }
        }
    }

    private static void right(int[][] copy, int d) {
        boolean[][] visited = new boolean[N][N];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if (copy[i][j] != 0) { // 숫자가 있는 것에 대해서
                    int curR = i;
                    int curC = j; // 현재 좌표

                    int newR = curR;
                    int newC = curC;

                    // direction만큼 이동
                    // 거꾸로해야할듯
                    // 위 -> 아래
                    // up, down, left, right 나눔
                    while (true){
                        newR = curR + dr[d];
                        newC = curC + dc[d];

                        // 범위 밖이면 break
                        // 앞에 자신과 다른수가있으면 break
                        if (!check(newR,newC)) break;
                        if (visited[newR][newC]) break;

                        // 0이면 계속이동
                        if (copy[newR][newC] == 0) {
                            copy[newR][newC] = copy[curR][curC];
                            copy[curR][curC] = 0;
                            curR = newR;
                            curC = newC;
                            continue;
                        }

                        // 자신과 같은수를 만남. -> 합침
                        if (copy[newR][newC] == copy[curR][curC]){
                            copy[newR][newC] *= 2;
                            copy[curR][curC] = 0;
                            visited[newR][newC] = true; // 합치고 그 라운드 visited
                            break;
                        }

                        if (copy[newR][newC] != copy[curR][curC]) break;
                        curR = newR;
                        curC = newC;
                    }
                }
            }
        }
    }

    private static boolean check(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}