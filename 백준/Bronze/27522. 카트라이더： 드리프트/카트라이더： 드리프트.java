import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int total = 0;
    static String[][] racer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        racer = new String[8][2];
        for (int i = 0; i < 8; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            racer[i][0] = st.nextToken();
            racer[i][1] = st.nextToken();
        }

        Arrays.sort(racer, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1[0].compareTo(o2[0]); // 사전순
            }
        });

        // 출력
        /*System.out.println();
        for (int i = 0; i < 8; i++) {
            System.out.println(racer[i][0] + " " + racer[i][1]);
        }
        System.out.println();*/

        int sumA = 0;
        int sumB = 0;
        int[] score = {10, 8, 6, 5, 4, 3, 2, 1, 0};
        int scoreIndex = 0;
        for (int i = 0; i < 8; i++) {
            if (racer[i][1].equals("R")){
                sumA += score[scoreIndex];
            }
            else{
                sumB += score[scoreIndex];
            }
            scoreIndex++;
        }

        /*bw.write(String.valueOf(sumA) + "\n");
        bw.write(String.valueOf(sumB) + "\n");*/

        if (sumA > sumB) {
            bw.write("Red");
        }
        else if (sumB > sumA){
            bw.write("Blue");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}

/*
 * 이미 성사된 사랑은 포기 x
 * K각 관계 x -> 이 경우 어떻게 처리?
 * 포기할수있게 하는 경우의 수 여러개 -> 애정도의 합 최소
 * 애정도의 합의 최솟값은?
 * */
