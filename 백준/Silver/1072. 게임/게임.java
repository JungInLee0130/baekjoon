import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static long X, Y;
    static int Z;
    static final int MAX_X = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Long.parseLong(st.nextToken());
        Y = Long.parseLong(st.nextToken());

        // Y : WIN
        Z = (int) (Y * 100 / X);


        if (Z >= 99) {
            System.out.println(-1);
            return;
        }

        int result = binarySearch();

        System.out.println(result);


        bw.flush();
        br.close();
        bw.close();
    }

    private static int binarySearch() {
        int left = 0;
        int right = MAX_X;
        int result = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int z = (int) ((Y + mid) * 100 / (X + mid));
            if (z > Z) {
                right = mid - 1;
            }
            else{ // 더 작은 경우
                result = mid + 1;
                left = mid + 1;
            }
        }
        return result;
    }
}