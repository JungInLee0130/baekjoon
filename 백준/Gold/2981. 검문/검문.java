import java.io.*;
import java.util.*;

public class Main {
    public static int EuclideanGCD(int A, int B) {
        int R = A % B;

        if (R == 0) {
            return B;
        }
        return EuclideanGCD(B, R);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] number = new int[N];
        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(number);

        ArrayList<Integer> result = new ArrayList<>();
        //int k = 0;
        //int index = 0;

        int min = number[0];
        for (int i = 0; i < N; i++) {
            number[i] = number[i] - min;
        }

        int GCD;

        // 최대 공약수 구하기
        if (N == 2) {
            GCD = number[1];
        }
        else {
            GCD = EuclideanGCD(number[1], number[2]);
            for (int i = 3; i < N; i++) {
                GCD = EuclideanGCD(GCD, number[i]);
            }
        }

        // 약수 구하기

        for (int i = 2; i <= GCD; i++) {
            if (GCD % i == 0) {
                result.add(i);
            }
        }


        /*while ((number[0] - k) > 1) { // 문제: M이 min보다 큰경우 탐색 불가
            if ((number[index] - k) % (number[0] - k) == 0) { // 모두 0으로 떨어지는 것
                // do nothing
                index++; // 다음 수 비교
            }
            else{ // 안떨어지면 : k++, 처음부터 비교
                k++;
                index = 0;
            }
            if (index == N) { // 모두 나누어 떨어져서 index가 끝에 도달
                result.add(number[0] - k); // number[0] - k : M
                index = 0;
                k++; // 다음 k++
            }
        }*/

        //Collections.sort(result);

        for (int i = 0; i < result.size(); i++) {
            bw.write(String.valueOf(result.get(i)) + " ");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
