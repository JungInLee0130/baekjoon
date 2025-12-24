import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        value = Integer.parseInt(br.readLine());

        getSnail();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(tx + " " + ty);

        br.close();
    }

    static int N, value;
    static int[][] map;
    static int tx, ty;
    private static void getSnail() {
        int num = 1;    // 넣을 숫자
        int move = 1;   // 이동할수있는 수
        int[] dx = {-1, 0, 1, 0};   // rotate 순서
        int[] dy = {0, 1, 0, -1};
        int sx = N / 2; // 초기좌표 x
        int sy = N / 2; // 초기좌표 y 
        int d = 0;      // 방향
        int rotateCount = 0;    // 방향 바꾼횟수가 2번 이상이면 한칸 더 늘어남.
        map = new int[N][N];

        map[sx][sy] = num;  // 처음부터 1 넣고 시작
        tx = sx + 1;
        ty = sy + 1;
        num += 1;

        while (num <= N * N) {
            for (int i = 1; i <= move; i++) {
                int nx = sx + dx[d];
                int ny = sy + dy[d];

                //print();

                map[nx][ny] = num;
                if (num == value){
                    tx = nx + 1;    // 실제좌표 : +1
                    ty = ny + 1;
                }
                num += 1;
                if (num > N * N) return;
                sx = nx;    // 좌표 갱신
                sy = ny;    
            }
            d = (d + 1) % 4;
            rotateCount += 1;
            if (rotateCount >= 2) {
                move += 1;          // 한칸 더 갈수있고
                rotateCount = 0;    // 방향 바꾼횟수 초기화
            }
        }
    }

    private static void print() {
        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}