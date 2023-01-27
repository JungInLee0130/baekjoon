import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        int count = 0;
        int allCount = 0;

        String[] nums = new String[N];
        int[][] baseball = new int[N][2];

        // 입력 받음
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 문제의 숫자 입력받음
            nums[i] = st.nextToken();

            // 스트라이크, 볼 개수 입력
            baseball[i][0] = Integer.parseInt(st.nextToken());
            baseball[i][1] = Integer.parseInt(st.nextToken());
        }



        // 123 ~ 987 까지 만족하는 수 찾아서 count++
        for (int num = 123; num <= 987; num++) {
            // 스트링 변환
            String str2 = String.valueOf(num);
            // 1. 중복 숫자 or 0 체크
            if (check(str2)) {
                continue;
            }

            count = 0;
            for (int i = 0; i < N; i++) {
                String str = nums[i];
                // 3스트라이크면 정답, 리턴
                if (baseball[i][0] == 3) {
                    allCount++;
                    break;
                }

                // 스트라이크, 볼 카운트
                int countStrike = 0;
                int countBall = 0;

                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        if (str.charAt(j) == str2.charAt(k)) { // 같을때, 위치도 같음
                            if (j == k) { // 위치같으면 strike
                                countStrike++;
                                continue;
                            }
                            // 아니면 ball
                            countBall++;
                        }
                    }
                }

                // 스트라이크와 볼카운트가 같음
                if (baseball[i][0] == countStrike
                        && baseball[i][1] == countBall) {
                    count++;
                }
            }

            if (count == N) {
                allCount++;
            }

        }

        bw.write(String.valueOf(allCount));


        bw.flush();
        br.close();
        bw.close();
    }


    private static boolean check(String str2) {
        // 1 ~ 9까지, 즉, 0이 들어가면안됨.
        for (int i = 0; i < 3; i++) {
            if (str2.charAt(i) - '0' == 0) {
                return true;
            }
        }
        // 나머지는 같은거 체크
        // if - elseif 는 작은거 -> 큰거 순으로
        if (str2.charAt(0) == str2.charAt(1)
                && str2.charAt(1) == str2.charAt(2)
                && str2.charAt(2) == str2.charAt(0)) { // 3개 모두 같은거 체크
            return true;
        }
        if (str2.charAt(0) == str2.charAt(1)) {
            return true; // 중복
        }
        if (str2.charAt(1) == str2.charAt(2)) {
            return true;
        }
        if (str2.charAt(0) == str2.charAt(2)) { // 2개 같은거 체크
            return true;
        }
        return false;
    }
}
