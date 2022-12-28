import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int[][] paper;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        paper = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        countNum(0, 0, N);

        bw.write(String.valueOf(minusOneCount) + "\n");
        bw.write(String.valueOf(zeroCount) + "\n");
        bw.write(String.valueOf(oneCount));


        bw.flush();
        br.close();
        bw.close();
    }

    private static int oneCount = 0;
    private static int zeroCount = 0;
    private static int minusOneCount = 0;
    private static void countNum(int row, int column, int size) {
        boolean isSame = judgeSame(row, column, size);
        int paperNum = paper[row][column];
        if (isSame) {
            if (paper[row][column] == 1) {
                oneCount++;
            } else if (paper[row][column] == 0) {
                zeroCount++;
            } else if (paper[row][column] == -1) {
                minusOneCount++;
            }
        }
        else{
            if (size == 1) {
                return;
            }
            int newSize = size / 3;
            countNum(row, column, newSize);
            countNum(row, column + newSize, newSize);
            countNum(row, column + newSize * 2, newSize);
            countNum(row + newSize, column, newSize);
            countNum(row + newSize, column + newSize, newSize);
            countNum(row + newSize, column + + newSize * 2, newSize);
            countNum(row + newSize * 2, column, newSize);
            countNum(row + newSize * 2, column + newSize, newSize);
            countNum(row + newSize * 2, column + newSize * 2, newSize);
        }
    }

    private static boolean judgeSame(int row, int column, int size) {
        int paperNum = paper[row][column];
        for (int i = row; i < row + size; i++) {
            for (int j = column; j < column + size; j++) {
                if (paper[i][j] == paperNum) {
                    continue;
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }
}
