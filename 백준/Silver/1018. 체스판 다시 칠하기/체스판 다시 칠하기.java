
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] chess;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // column
        N = Integer.parseInt(st.nextToken()); // row

        chess = new char[M][N];

        for (int row = 0; row < M; row++) {
            String str = br.readLine();
            for (int column = 0; column < N; column++) {
                chess[row][column] = str.charAt(column);
            }
        }

        // 올바른거 하나 두고 다른거 카운트
        char[][] blackChess = new char[8][8];
        char[][] whiteChess = new char[8][8];

        makingChess(blackChess, 'W');
        makingChess(whiteChess, 'B'); // 거꾸로 되므로 거꾸로 color 대입

        int subRow = M - 8;
        int subColumn = N - 8;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= subRow; i++) { // 2
            for (int j = 0; j <= subColumn; j++) { // 5
                min = Math.min(min,check(blackChess, i, j));
                min = Math.min(min,check(whiteChess, i, j));
            }
        }

        bw.write(String.valueOf(min));

        bw.flush();
        br.close();
        bw.close();
    }

    private static int check(char[][] answer, int row, int column) {
        int count = 0;
        for (int i = row; i < row + 8; i++) {
            for (int j = column; j < column + 8; j++) {
                if (answer[i - row][j - column] == chess[i][j]) {
                    continue;
                }
                count++;
            }
        }
        return count;
    }

    private static void makingChess(char[][] chess, char color) {
        chess[0][0] = color; // W

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (color == 'B') {
                    chess[i][j] = 'W';
                    color = 'W';
                } else {
                    chess[i][j] = 'B';
                    color = 'B';
                }
            }
            if (color == 'B') {
                color = 'W';
            } else {
                color = 'B';
            }
        }
    }


}
