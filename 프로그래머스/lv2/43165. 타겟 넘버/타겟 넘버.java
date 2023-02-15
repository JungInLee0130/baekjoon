import java.io.*;
class Solution {
    static int total = 0;
    static int[] arr;
    static int N;
    // 중복 순열
    public int solution(int[] numbers, int target) { //
        N = numbers.length;

        int[] operCount = new int[2];
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            operCount[0] = N - i; // N부터 : 앞에도 붙을수있다.
            operCount[1] = i; // 0부터
            perm2(0, numbers, target, operCount);
        }

        return total;
    }

    private void perm2(int cnt, int[] numbers, int target, int[] operCount) {
        if (cnt == N) {
            if (summation(numbers) == target) {
                total++;
            }
            return;
        }
        // 중복 뽑기됨.
        for (int i = 0; i < 2; i++) { // 0, 1
            if (operCount[i] > 0) { // 0보다 커야 arr에 포함시킴
                operCount[i]--;
                arr[cnt] = i + 1; // 1 , 2
                perm2(cnt + 1, numbers, target, operCount);
                arr[cnt] = 0;
                operCount[i]++;
            }
        }
    }

    private int summation(int[] numbers) {
        int sum = 0;
        for (int i = 0; i < N; i++) { // 연산수대로 돔.
            if (arr[i] == 1) { // +
                sum += numbers[i];
            }
            if (arr[i] == 2) { // -
                sum -= numbers[i];
            }
        }
        return sum;
    }
}