import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    static int N;
    static int[][] paper;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 100개

        paper = new int[101][101]; // 1 ~ 100

        // 도화지 가로세로 100
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int upLeft = Integer.parseInt(st.nextToken());
            int bottomLeft = Integer.parseInt(st.nextToken()); // 가로세로 10인 정사각형

            for (int r = upLeft; r < upLeft + 10; r++) {
                for (int c = bottomLeft; c < bottomLeft + 10; c++) {
                    paper[r][c] = 1;
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (paper[i][j] == 1) {
                    answer++;
                }
            }
        }

        System.out.println(answer);


        bw.flush();
        bw.close();
    }
}