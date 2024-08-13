import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] A = new int[3];

            for (int i = 0; i < 3; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }


            if (A[0] == 0 && A[1] == 0 && A[2] == 0) break;

            if (A[0] == A[1] && A[1] == A[2] && A[2] == A[0]) System.out.println("Equilateral");
            else if (A[0] + A[1] <= A[2] || A[1] + A[2] <= A[0] || A[2] + A[0] <= A[1]) System.out.println("Invalid");
            else if (A[0] == A[1] || A[1] == A[2] || A[2] == A[0]) System.out.println("Isosceles");
            else System.out.println("Scalene");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}