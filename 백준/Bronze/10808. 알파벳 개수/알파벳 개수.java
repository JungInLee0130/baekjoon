import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static String S;
    static int[] alpha;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        S = br.readLine();

        int count = 0;

        alpha = new int[26];
        for (int i = 0; i < S.length(); i++) {
            alpha[S.charAt(i) - 'a']++;
        }

        Arrays.stream(alpha).forEach(e -> System.out.print(e + " "));

        bw.flush();
        bw.close();
        br.close();
    }
}