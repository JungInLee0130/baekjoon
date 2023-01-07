class Solution {
  public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;

        // 하나의 최대공약수가 다른 배열원소에 나눠지지않아야함.
        int a = arrayA[0];
        for (int i = 1; i < arrayA.length; i++) {
            if (a > arrayA[i]) {
                a = GCD(a, arrayA[i]);
            } else {
                a = GCD(arrayA[i], a);
            }

        }

        int b = arrayB[0];
        for (int i = 1; i < arrayB.length; i++) {
            if (b > arrayB[i]) {
                b = GCD(b, arrayB[i]);
            } else {
                b = GCD(arrayB[i], b);
            }
        }

        if (a > b) {
            answer = a;

            for (int i = 0; i < arrayB.length; i++) {
                if (arrayB[i] % a == 0) {
                    return 0;
                }
            }
        }

        if (a < b) {
            answer = b;

            for (int i = 0; i < arrayA.length; i++) {
                if (arrayA[i] % b == 0) {
                    return 0;
                }
            }
        }


        return answer;
    }

    private int GCD(int A, int B) {
        if (B == 0) {
            return A;
        }
        return GCD(B, A % B);
    }
}