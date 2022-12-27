import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int r;
    private static int c;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int size = (int) Math.pow(2, N);

        z(size, 0, 0);

        bw.write(String.valueOf(answer));

        bw.flush();
        br.close();
        bw.close();
    }

    private static int answer = 0;
    private static void z(int size, int row, int column) {
        if (size == 1) {
            return;
        }
        int newSize = size / 2;
        if (r < row + newSize && c < column + newSize) { // 1사분면
            z(newSize, row, column);
        } else if (r < row + newSize && c >= column + newSize) { // 2사분면
            answer = answer + (size * size) / 4;
            z(newSize, row, column + newSize);
        } else if (r >= row + newSize && c < column + newSize) { // 3사분면
            answer = answer + (size * size) / 4 * 2;
            z(newSize, row + newSize, column);
        } else if (r >= row + newSize && c >= column + newSize) {// 4사분면
            answer = answer + (size * size) / 4 * 3;
            z(newSize, row + newSize, column + newSize);
        }
        // 그냥 재귀돌리면 메모리 초과. 세부범위를 나누자.
    }
}
