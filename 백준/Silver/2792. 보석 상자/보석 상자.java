import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] jewelies;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int start = 1;
        int end = 0;
        jewelies = new int[M];
        for (int i = 0; i < M; i++) {
            jewelies[i] = Integer.parseInt(br.readLine()); // 개수
            end = Math.max(jewelies[i], end);
        }

        // 한학생은 같은 색깔만 가질수있음.
        binarySearch(start, end);

        System.out.println(answer);


        bw.flush();
        br.close();
        bw.close();
    }

    // 기준 질투심
    private static void binarySearch(int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;

            // 최대 몇명가능
            long sum = getCount(mid);
            
            if (sum <= N) {
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }

        answer = Math.max(start, end);
    }

    private static long getCount(int mid) {
        long sum = 0;
        for (int jewel : jewelies) {
            if (jewel % mid == 0) {
                sum += jewel / mid; // 나누어 떨어지면 그 수 만큼
            }
            else{
                sum += jewel / mid + 1; // 나머지는 다른 학생에게
            }
        }

        return sum;
    }

    private static int answer;
}