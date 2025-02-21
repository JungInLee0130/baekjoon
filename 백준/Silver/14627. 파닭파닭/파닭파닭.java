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
            long mid = (low + high) / 2;

            long count = getCount(mid);

            if (count >= C) {
                answer = mid;
                low = mid + 1;
            }
            else if (count < C){
                high = mid - 1;
            }
        }

        long sum = 0;
        for (int i = 0; i < S; i++) {
            sum += len[i];
        }

        long leftLen = sum - answer * C;

        System.out.println(leftLen);

        br.close();
    }

    private static long getCount(long mid) {
        long count = 0;

        for (int i = 0; i < S; i++) {
            count += len[i] / mid;
        }

        return count;
    }
}