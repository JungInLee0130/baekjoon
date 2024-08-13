import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        if (N % 2 == 0) {
            System.out.println("CY");    
        }
        else{
            System.out.println("SK");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}