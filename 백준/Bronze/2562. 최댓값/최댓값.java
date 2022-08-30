import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        int[] num = new int[9];

        int max = 0;
        int maxIndex = 0;

        for (int i = 0; i < 9; i++) {
            int a = Integer.parseInt(br.readLine());

            if (a > max) {
                max = a;
                maxIndex = i;
            }
        }

        bw.write(String.valueOf(max) + "\n");
        bw.write(String.valueOf(maxIndex + 1));

        bw.flush();
        br.close();
        bw.close();
    }
}
