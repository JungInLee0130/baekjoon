import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());

            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[R][C];

            getSticker(sticker, R, C, br);


            int rotateCount = 0;

            while (rotateCount <= 3) {
                boolean canPaint = isPaint(sticker,R,C);

                if (canPaint) {
                    //print(sticker, R, C, "sticker");
                    paint(sticker, rrow, ccolumn, R, C);
                    break;
                }
                else{
                    rotateCount++;
                    if (rotateCount >= 4) {
                        break;
                    }
                    sticker = rotate(sticker, R, C);
                    int temp = R;
                    R = C;
                    C = temp;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1){
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static void getSticker(int[][] sticker, int R, int C, BufferedReader br) throws IOException {
        for (int r = 0; r < R; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                sticker[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static int rrow = 0;
    private static int ccolumn = 0;
    private static boolean isPaint(int[][] sticker, int R, int C) {
        for (int r = 0; r <= N - R; r++) {
            for (int c = 0; c <= M - C; c++) {
                boolean canPaint = painting(sticker, r, c, R, C);
                if (canPaint) {
                    rrow = r;
                    ccolumn = c;
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean painting(int[][] sticker, int mr, int mc, int R, int C) {
        for (int i = mr; i < mr + R; i++) { // 40
            for (int j = mc; j < mc + C; j++) { // 40
                if (sticker[i - mr][j - mc] == 1 && map[i][j] == 1){
                    return false;
                }
            }
        }
        return true;
    }


    private static int[][] rotate(int[][] sticker, int R, int C) {
        int[][] copy = new int[C][R];

        for (int c = 0; c < C; c++) {
            for (int r = 0; r < R; r++) {
                copy[c][r] = sticker[R - 1 - r][c];
            }
        }

        return copy;
    }

    private static void print(int[][] copy, int R, int C, String str) {
        System.out.println("------"+ str +"---------");

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                System.out.print(copy[r][c] + " ");
            }
            System.out.println();
        }
    }

    private static void paint(int[][] sticker, int row, int column, int R, int C) {
        for (int r = row; r < row + R; r++) {
            for (int c = column; c < column + C; c++) {
                if (sticker[r - row][c - column] == 1){
                    map[r][c] = 1;
                }
            }
        }
    }
}