import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    static int N;
    static int[] array;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        array = new int[N + 1]; // 1 ~ N
        for (int i = 1; i <= N; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        int index = 1;
        int num = 1;
        Stack<Integer> stack = new Stack<>();
        // 들어가고 생각
        StringBuilder sb = new StringBuilder();
        boolean isOk = true;
        while (index <= N) {
            // 같으면
            if (!stack.isEmpty()) {
                if (stack.peek() == array[index]) { // 같음
                    stack.pop();
                    sb.append("-\n");
                    index++;
                    continue;
                }
                if (stack.peek() > array[index]) {
                    isOk = false;
                    break;
                }
            }
            // 같은게 없으면 push
            stack.push(num++);
            sb.append("+\n");
        }

        if (isOk) {
            System.out.println(sb);
            return;
        }
        System.out.println("NO"); // 불가능

        bw.flush();
        bw.close();
        br.close();
    }
}