import java.io.*;
import java.util.*;

class Main {
    static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        A = new int[10];

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        int[] psum = new int[10];
        psum[0] = A[0];
        for (int i = 1; i < 10; i++) {
            psum[i] = psum[i - 1] + A[i];
        }

        for (int i = 0; i < 10; i++) {
            int num = Math.abs(psum[i] - 100);
            int sub = Math.abs(min - 100);
            if (num < sub){
                min = psum[i];
            } else if (num == sub && psum[i] > min) {
                min = psum[i];
            }
            else{
                break;
            }
        }

        System.out.println(min);

        br.close();
    }
}