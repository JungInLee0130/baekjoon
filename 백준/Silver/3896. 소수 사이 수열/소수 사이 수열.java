import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static boolean[] prime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        eratos();


        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int num = Integer.parseInt(br.readLine());

            // 소수 p, p + n 사이의 합성수들의 개수 + 1 리턴, 없으면 0
            sb.append(getLength(num) + "\n");
        }

        System.out.println(sb.toString());


        bw.flush();
        br.close();
        bw.close();
    }

    private static boolean[] isPrime;
    private static void eratos() {
        prime = new boolean[1299710]; // 1 ~ 1299709
        isPrime = new boolean[1299710];

        for (int i = 2; i < 1299710; i++) {
            if (!prime[i]) {
                isPrime[i] = true;
                for (int j = i; 2 < i * j && i * j < 1299710; j++) {
                    prime[i * j] = true;
                }
            }
        }
    }

    private static int getLength(int num) {
        // 소수부터 시작
        // num보다 작은
        // num보다 큰 소수까지
        if (isPrime[num]) return 0;

        int left = getDownPrime(num);
        int right = getUpPrime(num);

        if (left <= 1 || right >= 1299710) {
            return 0;
        }

        return right - left;
    }

    private static int getUpPrime(int num) {
        int left = num + 1;
        int right = 1299709;

        while (left <= right) {
            // 소수이면
            if (isPrime[left]) {
                return left;
            }
            else{ // 아니면
                left++;
            }
        }

        return 0;
    }

    private static int getDownPrime(int num) {
        int left = 1;
        int right = num - 1;

        while (left <= right) {
            // 소수임
            if (isPrime[right]) {
                return right;
            }
            else{ // 소수 아님
                right--;
            }
        }

        return 0;
    }
}