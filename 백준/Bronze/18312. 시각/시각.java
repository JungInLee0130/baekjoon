import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 24 * 60 * 60 = 86400
        int hourCount = N + 1; // 0 더해서
        for (int i = 0; i <= N; i++) {
            int ten = i / 10;
            int one = i % 10;

            if (ten == K || one == K) {
                hourCount -= 1;
            }
        }

        int minuteCount = 60;
        for (int i = 0; i <= 59; i++) {
            int ten = i / 10;
            int one = i % 10;

            if (ten == K || one == K) {
                minuteCount -= 1;
            }
        }

        int secondCount = minuteCount;

        int leftCount = hourCount * minuteCount * secondCount;
        int totalCount = (N + 1) * 60 * 60;

        System.out.println(totalCount - leftCount);

        br.close();
    }
    static int N, K;
}