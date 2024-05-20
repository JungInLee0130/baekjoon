import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static char[][] letters;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        letters = new char[5][15];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 15; j++) {
                letters[i][j] = ' ';
            }
        }

        for (int i = 0; i < 5; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                letters[i][j] = str.charAt(j);
            }
        }

        for (int col = 0; col < 15; col++) {
            int isBlank = 0;
            for (int row = 0; row < 5; row++) {
                if (letters[row][col] == ' ') {
                    isBlank++;
                    continue;
                }
                bw.write(letters[row][col]);
            }
            if (isBlank == 5) {
                break;
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}