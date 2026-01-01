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

        getCount();


        br.close();
    }

    private static void getCount() {
        int hourCount = N;
        hourCount = getCount(hourCount + 1, hourCount + 1);

        int minuteCount = 60;
        minuteCount = getCount(minuteCount, minuteCount);

        int secondCount = minuteCount;

        int totalCount = (N + 1) * 60 * 60;
        int leftCount = hourCount * minuteCount * secondCount;

        System.out.println(totalCount - leftCount);
    }

    private static int getCount(int time, int totalCount) {
        for (int h = 0; h < time; h++) {
            String value = String.format("%02d", h);
            for (int i = 0; i < value.length(); i++) {
                if (value.charAt(i) - '0' == K) {
                    totalCount -= 1;
                    break;
                }
            }
        }
        return totalCount;
    }

    static int N, K;
}