import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int X, Y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        // 거꾸로 만드는함수
        System.out.println(rev(rev(X) + rev(Y)));
    }

    private static int rev(int x) {
        Stack<Integer> stack = new Stack<>();
        while (x > 0) {
            int q = x / 10; // 몫
            int r = x % 10; // 나머지

            stack.push(r); // 일의자리부터 넣기
            if (q == 0) {
                break;
            }
            x = q; // 다음 자리수 알아보기
        }

        int len = stack.size();

        int num = 0;
        // stack은 일의자리부터 넣어서 reverse하면 일의자리부터나온다
        for (int i = 0; i < len; i++) {
            num = num + stack.pop() * (int)Math.pow(10, i);
        }

        return num;
    }
}