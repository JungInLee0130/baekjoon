import java.io.*;
import java.util.*;

class Main {
    static StringTokenizer st;
    static int S, C;
    static long[] len;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // C개에 같은 길이의 파를 넣고 남은 len의 합을 구하는 문제인듯.
        long max = 0;
        len = new long[S];
        for (int i = 0; i < S; i++) {
            len[i] = Long.parseLong(br.readLine());
            max = Math.max(max, len[i]);
        }

        long low = 1;
        long high = max;

        long answer = 0;

        while (low <= high) {
            long mid = (low + high) / 2; // 1 ~ 10억 // 10억 + 10억이면 안넘긴함.

            long count = getCount(mid);

            if (count >= C) {
                answer = mid;
                low = mid + 1;
            }
            else if (count < C){
                high = mid - 1;
            }
        }

        long leftLen = 0;

        long count = getCount(answer);

        for (int i = 0; i < S; i++) {
            leftLen += len[i] % answer;
        }

        if (count == C){

        }
        else{
            while (count > C) {
                leftLen += answer;
                count--;
            }
        }


        System.out.println(leftLen);


        br.close();
    }

    private static long getCount(long mid) {
        long count = 0;

        for (int i = 0; i < S; i++) {
            count += len[i] / mid; // 개수가 C개여야함 그중 최대 // 많은 양의 파를 넣기때문에
        }

        return count;
    }
}