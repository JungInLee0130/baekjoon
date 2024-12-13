import java.io.*;
import java.util.*;

public class Main {
    static final String ACCEPTABLE = " is acceptable.";
    static final String NOT_ACCEPTABLE = " is not acceptable.";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true){
            String str = br.readLine();

            if (str.equals("end")) break;

            // 1. 모음을 하나 포함해야함.
            boolean isAccepted = ruleOne(str);

            if (!isAccepted){
                System.out.println("<"+str+">" + NOT_ACCEPTABLE);
                continue;
            }

            // 2. 모음 또는 자음이 연속해서 3개가 오면안됨.
            if (str.length() >= 3){
                isAccepted = ruleTwo(str);

                if (!isAccepted){
                    System.out.println("<"+str+">" + NOT_ACCEPTABLE);
                    continue;
                }
            }

            // 3. 같은 글자가 연속적으로 오면 안됨. ee oo는 제외
            if (str.length() >= 2) {
                isAccepted= ruleThree(str);

                if (!isAccepted) {
                    System.out.println("<"+str+">" + NOT_ACCEPTABLE);
                    continue;
                }
            }

            // 모두 만족
            System.out.println("<"+str+">" + ACCEPTABLE);
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static boolean ruleThree(String str) {
        for (int i = 0; i < str.length() - 1; i++) {
            char firstLetter = str.charAt(i);
            char secondLetter = str.charAt(i + 1);

            if (firstLetter == secondLetter) {
                if (firstLetter == 'e' || firstLetter == 'o') {
                    // continue;
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean ruleTwo(String str) {
        for (int i = 1; i < str.length() - 1; i++) {
            // 모음
            if (isVowel(str.charAt(i - 1)) && isVowel(str.charAt(i)) && isVowel(str.charAt(i + 1))){
                return false;
            }

            // 자음
            if (!isVowel(str.charAt(i - 1)) && !isVowel(str.charAt(i)) && !isVowel(str.charAt(i + 1))) {
                return false;
            }
        }
        return true;
    }

    private static boolean ruleOne(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (isVowel(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean isVowel(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') return true;
        return false;
    }



}