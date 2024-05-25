import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int[] ham;
    static int[] bev;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ham = new int[3];
        for (int i = 0; i < 3; i++) {
            ham[i] = Integer.parseInt(br.readLine());
        }

        bev = new int[2];
        for (int i = 0; i < 2; i++) {
            bev[i] = Integer.parseInt(br.readLine());
        }

        int[] result = new int[6];

        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                result[k] = ham[i] + bev[j] - 50;
                k++;
            }
        }

        Arrays.sort(result);

        System.out.println(result[0]);

        bw.flush();
        bw.close();
        br.close();
    }
}