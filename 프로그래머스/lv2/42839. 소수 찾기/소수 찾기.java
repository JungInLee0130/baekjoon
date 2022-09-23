import java.io.*;
import java.util.*;

public class Solution {

    public static int solution(String numbers) {
        int len = numbers.length();
        // String numbers -> int[] number
        int[] number = new int[len];
        for (int i = 0; i < len; i++) {
            number[i] = numbers.charAt(i) - '0';
        }

        visited = new boolean[len];

        Arrays.fill(visited, false);

        Combination(number);


        int count = numberCase2.size();

        return count;
    }
    // 백트래킹? -> DFS?
    private static boolean[] visited;
    private static int count = 0; // 소수의 갯수

    private static void Combination(int[] number) {
        for (int i = 0; i < number.length; i++) {
            int digit = i + 1;
            int[] numberCase = new int[digit];
            DFS(number, 0, digit, numberCase);
        }

    }

    private static HashSet<Integer> numberCase2 = new HashSet<>();
    private static void DFS(int[] number, int depth, int digit, int[] numberCase) {
        // depth: 변하는 길이
        // digit: 목표 자릿수
        if (depth == digit) {
            int num = 0;
            for (int i = 0; i < numberCase.length; i++) {
                if (numberCase[i] == 0 && i == 0) {
                    continue;
                }
                num += numberCase[i] * Math.pow(10, numberCase.length - i - 1);
            }

            int count = 0;
            for (int i = 2; i < num; i++) {
                if (num % i == 0) {
                    count++;
                    break;
                }
            }
            if (count == 0 && num != 1 && num != 0) {
                numberCase2.add(num);
            }
            return;
        }

        for (int i = 0; i < number.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                numberCase[depth] = number[i];
                DFS(number, depth + 1, digit, numberCase);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = "011";


        bw.write(String.valueOf(solution(str)) + "\n");

        Iterator iter = numberCase2.iterator();
        while (iter.hasNext()) {
            bw.write(String.valueOf(iter.next()) + " ");
        }

        bw.write("\n");
        bw.flush();
        br.close();
        bw.close();
    }
}
