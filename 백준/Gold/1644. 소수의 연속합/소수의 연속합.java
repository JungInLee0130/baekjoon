
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        ArrayList<Integer> primeNumberArray = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (primeNumber(i)) {
                primeNumberArray.add(i);
            }
        }

        int len = primeNumberArray.size();
        int[] primeNumberarr = new int[len + 1];

        for (int i = 0; i < len; i++) {
            primeNumberarr[i] = primeNumberArray.get(i);
        }

        int start = 0;
        int end = 0;
        int sum = 0;
        int count = 0;

        while (start <= end) {
            if (sum == N) {
                count++;
            }
            if (sum >= N) {
                sum -= primeNumberarr[start++];
            } else if (end == len) {
                break;
            } else{ // sum < N
                sum += primeNumberarr[end++];
            }
        }

        bw.write(String.valueOf(count));

        bw.flush();
        br.close();
        bw.close();
    }

    private static boolean primeNumber(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
