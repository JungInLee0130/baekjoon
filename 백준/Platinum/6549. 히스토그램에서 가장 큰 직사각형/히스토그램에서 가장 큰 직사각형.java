import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] heights;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken()); // 10만이하

            if (N == 0) {
                break;
            }

            heights = new int[N]; // 10억보다작은 자연수
            for (int i = 0; i < N; i++) {
                heights[i] = Integer.parseInt(st.nextToken());
            }

            Stack<Integer> stack = new Stack<>();
            long maxArea = 0;
            for (int i = 0; i < N; i++) {
                while (!(stack.isEmpty()) && heights[stack.peek()] >= heights[i]) {
                    int h = heights[stack.pop()];

                    long w = stack.isEmpty() ? i : i - 1 - stack.peek();

                    maxArea = Math.max(maxArea, w * h);
                }
                stack.push(i);
            }

            while (!stack.isEmpty()) {
                int h = heights[stack.pop()];

                long w = stack.isEmpty() ? N : N - 1 - stack.peek();

                maxArea = Math.max(maxArea, w * h);
            }

            System.out.println(maxArea);
        }

        bw.flush();
        br.close();
        bw.close();
    }
}