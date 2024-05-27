import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int B;
    static String N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = st.nextToken();
        B = Integer.parseInt(st.nextToken());

        int len = N.length();

        int result = 0;
        for (int i = 0; i < len; i++) {
            char c = N.charAt(i);
            // 알파벳 대문자인경우
            if ('A' <= c && c <= 'Z') {
                int num = c - 'A' + 10;
                result += num * (int)Math.pow(B, len - 1 - i);
            }
            else{
                int num = c - '0';
                result += num * (int) Math.pow(B, len - 1 - i);
            }
        }

        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
        br.close();
    }
}