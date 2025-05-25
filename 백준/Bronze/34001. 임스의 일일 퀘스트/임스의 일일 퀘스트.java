import java.io.*;
import java.util.*;

class Main {
    static int L;
    static int[] level = {200, 210, 220, 225, 230, 235, 245, 250, 260, 265, 270, 275, 280, 285, 290, 295, 300};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        L = Integer.parseInt(br.readLine());

        int idx = 0;
        for (int i = 0; i < level.length - 1; i++) {
            if (level[i] <= L && L < level[i + 1]) {
                idx = i;
                break;
            }
        }
        if (level[level.length - 1] <= L) {
            idx = level.length - 1;
        }

        Stack<Integer> stack = new Stack<>();

        for (int i = level.length - 1; i > idx; i--) {
            stack.push(0);
        }

        int count = 500;
        for (int i = idx; i >= 0; i--) {
            if (i == 6 || i == 7 || i == 15 || i == 16) continue;
            int sub = idx - i;
            if (sub > 2) sub = 2;
            stack.push(count - 200 * sub);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(stack.pop() + " ");
        }

        sb.append("\n");

        for (int i = 0; i < 7; i++) {
            sb.append(stack.pop() + " ");
        }

        System.out.print(sb);

        br.close();
    }
}