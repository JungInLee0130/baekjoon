import java.io.*;
import java.util.*;

public class Main {
    static String[] color = {
            "black", "brown", "red", "orange", "yellow", "green", "blue",
            "violet", "grey", "white"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        String one = br.readLine();
        String two = br.readLine();
        String three = br.readLine();

        for (int i = 0; i < 10; i++) {
            if (one.equals(color[i])) {
                sb.append(i);
                break;
            }
        }

        for (int i = 0; i < 10; i++) {
            if (two.equals(color[i])) {
                sb.append(i);
                break;
            }
        }

        long answer = Integer.parseInt(sb.toString());

        for (int i = 0; i < 10; i++) {
            if (three.equals(color[i])) {
                answer *= Math.pow(10, i);
                break;
            }
        }

        System.out.println(answer);
    }
}