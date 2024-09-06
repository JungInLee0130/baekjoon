import java.io.*;
import java.util.*;

public class Main {
    static int N, C;
    static int[] routers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        routers = new int[N];
        for (int i = 0; i < N; i++) {
            routers[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(routers);

        int dis = binarySearch(1, routers[N - 1] - routers[0] + 1);

        System.out.println(dis);

        bw.flush();
        bw.close();
        br.close();
    }


    private static int binarySearch(int left, int right) {
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            int count = getCount(mid);

            if (count >= C) { // 개수 더 많음 ->
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }


    private static int getCount(int mid) {
        int position = 0;
        int count = 1;

        for (int i = 1; i < N; i++) {
            int sub = routers[i] - routers[position] + 1;
            if (sub > mid) {
                position = i;
                count++;
            }
        }

        return count;
    }
}