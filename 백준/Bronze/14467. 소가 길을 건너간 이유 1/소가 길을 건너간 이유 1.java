import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] locationAndTimes;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        locationAndTimes = new int[11][2];  // 1~10/ 2

        // 위치 -1로 초기화
        for (int i = 1; i <= 10; i++) {
            locationAndTimes[i][0] = -1;
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int location = Integer.parseInt(st.nextToken());

            // 처음 들어왔으면
            if (locationAndTimes[num][0] == -1) {
                locationAndTimes[num][0] = location;
                continue;
            }

            if (locationAndTimes[num][0] != location) {
                locationAndTimes[num][0] = location;
                locationAndTimes[num][1] += 1;
            }
        }

        int result = 0;
        for (int i = 1; i <= 10; i++) {
            result += locationAndTimes[i][1];
        }

        System.out.println(result);

        br.close();
    }
}