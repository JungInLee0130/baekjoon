import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        int[] num = new int[10];

        for (int i = 0; i < 10; i++) {
            int a = Integer.parseInt(br.readLine());
            num[i] = a % 42;
        }

        Arrays.sort(num);

        int count = 1;

        for (int i = 0; i < 9; i++) {
            if (num[i] != num[i + 1]) {
                count++;
            }
        }

        bw.write(String.valueOf(count));


        bw.flush();
        br.close();
        bw.close();
    }
}
