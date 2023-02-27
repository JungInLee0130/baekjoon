import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] board;
    static int[][] copyMap;
    static int[][] black;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        black = new int[N][2];
        // 검정 스카프 왼쪽 아래 좌표 구함.
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 양쪽 +2 추가 하므로 좌표 늘린다.
            black[i][0] = Integer.parseInt(st.nextToken()) + 1;
            black[i][1] = Integer.parseInt(st.nextToken()) + 1;
        }

        // 양쪽에 +2를 추가해야할듯
        board = new int[102][102];

        for (int i = 0; i < N; i++) {
            int c = black[i][0];
            int r = black[i][1];
            for (int row = r; row < r + 10; row++) {
                for (int column = c; column < c + 10; column++) {
                    board[row][column] = 1; // 검은색 스카프 부분 1로 초기화
                }
            }
        }



        // 3인 원소 세기
        total = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (board[i][j] == 1){
                    for (int d = 0; d < 4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];

                        if (board[nr][nc] == 0){
                            total++;
                        }
                    }
                }
            }
        }
        


        System.out.println(total);

        bw.flush();
        br.close();
        bw.close();
    }


    static int total;
    // copyMap
    // 0이 있는 바깥쪽 모두 bfs로 훑으면서 만나는 1 -> 3
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};


}

// bfs 쓸필요도 없다는게 에바인듯.
/* 검은색 스카프 최소한만 사용. 둘레의 길이 측정.
 * 1. 검은색 스카프의 수 (100이하)
 * 2. 검은색 스카프가 놓인 위치 : (왼쪽아래의 x,y 좌표) (10x10)
 *
 * 둘레의 길이 출력
 *
 *
 * */

/*
4
3 7
5 2
15 7
13 14
*/