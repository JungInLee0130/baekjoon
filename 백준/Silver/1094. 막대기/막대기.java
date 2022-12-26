import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int X = Integer.parseInt(br.readLine());

        int original = 64;

        int sum = 0;
        int count = 0;
        if (original == X) {
            count = 1;
        }
        else {
            while (true) {
                if (sum == X) {
                    break;
                }
                original = original >> 1;
                if ((sum | original) > X) {
                } else if ((sum | original) <= X) {
                    sum = sum | original;
                    count++;
                }
            }
        }

        bw.write(String.valueOf(count));

        bw.flush();
        br.close();
        bw.close();
    }
}
