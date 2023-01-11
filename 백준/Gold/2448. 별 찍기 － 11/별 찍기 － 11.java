import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int N;
    static int[][] star;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        star = new int[N][2 * N];
        StringBuilder sb = new StringBuilder();

        // 로직
        drawStar(0, 0, N / 3);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                sb.append(star[i][j] == 1 ? "*" : " ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());

        bw.flush();
        br.close();
        bw.close();
    }

    static int[][] DB = {{0, 0, 1, 0, 0}, {0, 1, 0, 1, 0}, {1, 1, 1, 1, 1}};

    private static void drawStar(int r, int c, int n) {
        if (n == 1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    star[r + i][c + j] = DB[i][j];
                }
            }
            return;
        } else {
            drawStar(r, c + 3 * n / 2, n / 2);
            drawStar(r + 3 * n / 2, c, n / 2);
            drawStar(r + 3 * n / 2, c + 3 * n, n / 2);
        }
    }

}
