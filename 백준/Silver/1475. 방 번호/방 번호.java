import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static String str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine(); // 방번호

        // 0 ~ 9 한세트, 6 == 9 사용가능

        HashMap<Integer, Integer> map = new HashMap<>();

        int len = str.length();

        for (int i = 0; i <= 9; i++) {
            map.put(i, 0); // 0 초기화
        }

        for (int i = 0; i < len; i++) {
            int num = str.charAt(i) - '0';
            map.put(num, map.get(num) + 1); // default null;
        }
        // hashmap에 대입

        int max = 0;
        int sixAndNine = 0;
        int total = 0;
        for (int i = 0; i <= 9; i++) {
            if (i == 6 || i == 9) {
                sixAndNine = sixAndNine + map.get(i); // 둘이 합한값
            }
            else{
                max = Math.max(max, map.get(i)); // 최대값을 정함
            }
        }

        if (sixAndNine <= max * 2) {
            total = max;
        }
        else{
            total = max;
            double rest = sixAndNine - max * 2;
            total = total + (int) (Math.round(rest / 2)); // 몫과 나머지
        }

        System.out.println(total);
    }
}