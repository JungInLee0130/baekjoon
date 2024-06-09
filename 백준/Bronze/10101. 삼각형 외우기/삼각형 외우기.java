import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = new int[3];
        int sumAngle = 0;
        for (int i = 0; i < 3; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sumAngle += arr[i];
        }

        if (sumAngle == 180) {
            if (arr[0] == 60 && arr[1] == 60 && arr[2] == 60) {
                System.out.println("Equilateral");
            } else if (arr[0] == arr[1] || arr[1] == arr[2] || arr[2] == arr[0]) {
                System.out.println("Isosceles");
            } else{
                System.out.println("Scalene");
            }
        }
        else{
            System.out.println("Error");
        }


        bw.flush();
        bw.close();
        br.close();
    }
}