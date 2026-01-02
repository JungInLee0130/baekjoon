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
        int hoursWithoutK = getCount(N, hourCount);

        int minuteCount = 60;
        int minutesWithoutK = getCount(59, minuteCount);

        int secondCount = 60;
        int secondsWithoutK = minutesWithoutK;

        long totalCount = (long) hourCount * minuteCount * secondCount;
        long leftCount = (long) hoursWithoutK * minutesWithoutK * secondsWithoutK;

        System.out.println(totalCount - leftCount);

        br.close();
    }

    private static int getCount(int N, int count) {
        for (int i = 0; i <= N; i++) {
            int ten = i / 10;
            int one = i % 10;

            if (ten == K || one == K) {
                count -= 1;
            }
        }
        return count;
    }

    static int N, K;
}