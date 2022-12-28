import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static long C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        result = divideNum(A, B);

        bw.write(String.valueOf(result));

        bw.flush();
        br.close();
        bw.close();
    }

    private static long result;
    private static long divideNum(long A, long times) {
        if (times == 1) {
            return A % C;
        }

        long newTimes = times / 2;
        long num = divideNum(A, newTimes);

        if (times % 2 == 1) {
            return ((num * num % C) * (A % C)) % C;
        }

        return num * num % C;

    }

}
