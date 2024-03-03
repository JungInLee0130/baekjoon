import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = 0;

        for (int i = 0; i < 8; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                char c = str.charAt(j);
                if (c == 'F') {
                    if (i % 2 == 0){
                        if (j % 2 == 0) {
                            count++;
                        }
                    }
                    else{
                        if (j % 2 != 0) {
                            count++;
                        }
                    }
                }
            }
        }

        System.out.println(count);

    }
}