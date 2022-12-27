import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int[][] quadTree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        quadTree = new int[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                quadTree[i][j] = str.charAt(j) - '0';
            }
        }

        divideAndConquer(0,0,N);

        bw.flush();
        br.close();
        bw.close();
    }


    private static void divideAndConquer(int row, int column, int size) {
        boolean same = isSame(row, column, size);
        if (same) {

            if (quadTree[row][column] == 1) {
                System.out.print(1);
            } else {
                System.out.print(0);
            }

        } else {
            System.out.print("(");
            int newSize = size / 2;
            divideAndConquer(row, column, newSize);
            divideAndConquer(row, column + newSize, newSize);
            divideAndConquer(row + newSize, column, newSize);
            divideAndConquer(row + newSize, column + newSize, newSize);
            System.out.print(")");
        }

    }

    private static boolean isSame(int row, int column, int size) {
        int sameNum = quadTree[row][column];

        for (int i = row; i < row + size; i++) {
            for (int j = column; j < column + size; j++) {
                if (quadTree[i][j] == sameNum) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
