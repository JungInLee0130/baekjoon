
import java.io.*;

public class Main {
    static int N;
    static char[][] candy;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        // 캔디 위치 입력받음
        candy = new char[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                candy[i][j] = str.charAt(j);
            }
        }

        int max = 0;

        // 바꾸기전 카운트
        for (int i = 0; i < N; i++) {
            max = Math.max(max, countColumn(i));
        }
        for (int i = 0; i < N; i++) {
            max = Math.max(max, countRow(i));
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j + 1 < N) { // 행 go
                    swap(i, j, i, j + 1); // 오른쪽이랑 swap
                    max = Math.max(max, countColumn(i));
                    max = Math.max(max, countRow(j));
                    max = Math.max(max, countRow(j + 1));
                    swap(i, j, i, j + 1); // 복귀
                }
                if (i + 1 < N) {
                    swap(i, j, i + 1, j); // 아래쪽이랑 swap
                    max = Math.max(max, countColumn(i));
                    max = Math.max(max, countColumn(i + 1));
                    max = Math.max(max, countRow(j));
                    swap(i, j, i + 1, j); // 복귀
                }
            }
        }

        bw.write(String.valueOf(max));

        bw.flush();
        br.close();
        bw.close();
    }

    private static int countColumn(int x) {
        char color = candy[x][0];

        int max = 0;
        int count = 1;
        for (int column = 1; column < N; column++) {
            if (color != candy[x][column]) { // 색깔을 다른거 만나면 교체후 그 색깔로 계속 카운트
                color = candy[x][column];
                max = Math.max(max,count);
                count = 1;
                continue;
            }
            count++; // 같으면 계속 카운트
        }

        return Math.max(count, max);
    }

    private static int countRow(int y) {
        char color = candy[0][y];

        int count = 1;
        int max = 0;
        // 세로
        for (int row = 1; row < N; row++) {
            if (color != candy[row][y]) {
                color = candy[row][y];
                max = Math.max(count, max);
                count = 1;
                continue;
            }
            count++;
        }

        max = Math.max(count, max);

        return max;
    }

    private static void swap(int x, int y, int newX, int newY) {
        char temp = candy[x][y];
        candy[x][y] = candy[newX][newY];
        candy[newX][newY] = temp;
    }


}
