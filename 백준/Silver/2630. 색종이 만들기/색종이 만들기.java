import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int[][] colorPaper;
    private static int blueCount = 0;
    private static int whiteCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        colorPaper = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                colorPaper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //------------------------------------------------------------

        same(0,0,N);

        bw.write(String.valueOf(whiteCount) + "\n" + String.valueOf(blueCount));

        bw.flush();
        br.close();
        bw.close();
    }

    private static void same(int row, int column, int N) {
        boolean isSame = dividePaper2(row, column, N);
        if (isSame) {
            if (colorPaper[row][column] == 1) {
                blueCount++;
            } else {
                whiteCount++;
            }
        }
        else {
            int newSize = N / 2;
            same(row, column, newSize);
            same(row, column + newSize, newSize);
            same(row + newSize, column, newSize);
            same(row + newSize, column + newSize, newSize);
        }
    }

    private static boolean dividePaper2(int row, int column, int size) {

        int standardColor = colorPaper[row][column]; // 기준

        for (int i = row; i < row + size; i++) {
            for (int j = column; j < column + size; j++) {
                if (colorPaper[i][j] == standardColor) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
