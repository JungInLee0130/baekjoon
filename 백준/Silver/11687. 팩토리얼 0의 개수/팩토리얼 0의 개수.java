import java.io.*;
import java.util.*;

public class Main {
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        M = Integer.parseInt(br.readLine());

        // 팩토리얼중에 0의개수가 M인 N을 출력
        // 솔직히 0의 개수는 2 x 5 의 지수개수
        // 2: 2 4 6 8...
        // 5: 5 10 15 20...
        // 5의개수 = 0의 개수
        System.out.println(binarySearch());


        bw.flush();
        br.close();
        bw.close();
    }

    private static int binarySearch() {
        // 기준 : 값
        int left = 1;
        int right = M * 5;
        int N = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            int count = getFive(mid);

            if (count == M){
                N = mid;
                right = mid - 1;
            }
            else if (count > M){
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }

        return N;
    }

    private static int getFive(int mid) {
        // 5의 제곱
        int count = 0;
        for (int i = 5; i <= mid; i *= 5) {
            count += mid / i;
        }
        return count;
    }
}