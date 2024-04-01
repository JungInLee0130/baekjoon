import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static String str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();
        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < 26; i++) {
            hashMap.put((char)('A' + i), 0);
        }
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if(hashMap.containsKey(c)){
                hashMap.put(c, hashMap.get(c) + 1);
            }
        }

        boolean isTrue = true;
        if (len % 2 == 0) {
            for (int i = 0; i < 26; i++) {
                if (hashMap.get((char) ('A' + i)) % 2 != 0) {
                    isTrue = false;
                    break;
                }
            }
            if (isTrue) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 26; i++) {
                    if (hashMap.get((char) ('A' + i)) > 0) {
                        for (int j = 0; j < hashMap.get((char) ('A' + i)) / 2; j++) {
                            sb.append((char) ('A' + i));
                        }
                    }
                }
                int halflen = sb.toString().length();
                for (int i = halflen - 1; i >= 0; i--) {
                    sb.append(sb.toString().charAt(i));
                }

                System.out.println(sb.toString());
            }
            else{
                System.out.println("I'm Sorry Hansoo");
            }
        } else {
            int oneCount = 0;
            char letter = 0;
            for (int i = 0; i < 26; i++) {
                if (hashMap.get((char) ('A' + i)) % 2 != 0) {
                    if (oneCount == 1) {
                        isTrue = false;
                        break;
                    }
                    letter = (char) ('A' + i);
                    oneCount++;
                }
            }

            if (isTrue) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 26; i++) {
                    if (hashMap.get((char) ('A' + i)) > 0) {
                        for (int j = 0; j < hashMap.get((char) ('A' + i)) / 2; j++) {
                            sb.append((char) ('A' + i));
                        }
                    }
                }
                sb.append(letter);
                int halflen = sb.toString().length();
                for (int i = halflen - 2; i >= 0; i--) {
                    sb.append(sb.toString().charAt(i));
                }

                System.out.println(sb.toString());
            }
            else{
                System.out.println("I'm Sorry Hansoo");
            }
        }
    }
}