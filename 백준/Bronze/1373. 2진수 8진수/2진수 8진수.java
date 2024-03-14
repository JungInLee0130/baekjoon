import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static String binary;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        binary = br.readLine();

        // 8진수 변환
        // 000 -> 0

        int len = binary.length();

        Stack<Integer> stack = new Stack<>();

        int index = len - 1; // 끝에서부터

        int count = 0;
        int sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            int num = binary.charAt(i) - '0';
            // count : 0 <= count <= 2
            sum += num * Math.pow(2, count);
            count++;
            if (count == 3 || i == 0) { // 3개거나 끝에 도달했거나
                stack.push(sum);
                count = 0;
                sum = 0;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb.toString());
    }
}