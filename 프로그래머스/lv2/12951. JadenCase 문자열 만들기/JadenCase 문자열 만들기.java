

import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";

        StringBuilder sb = new StringBuilder();


        for (int i = 0; i < s.length(); i++) {
            // 그냥 i == 0일때 0이 아닐때 나누자.
            if (i == 0) {
                if ('a' <= s.charAt(i) && s.charAt(i) <= 'z') {
                    sb.append(String.valueOf((char)(s.charAt(i) - ('a' - 'A')))); // 첫문자인데 소문자인경우
                } else { // 그냥 공백 + 무난
                    sb.append(String.valueOf(s.charAt(i)));
                }
            }
            else {
                if (s.charAt(i - 1) == ' ' && 'a' <= s.charAt(i) && s.charAt(i) <= 'z') {
                    sb.append(String.valueOf((char) (s.charAt(i) - ('a' - 'A')))); // 앞에 공백이있는데 소문자
                }
                else if (s.charAt(i - 1) != ' ' && 'A' <= s.charAt(i) && s.charAt(i) <= 'Z') {
                    sb.append(String.valueOf((char) (s.charAt(i) + ('a' - 'A')))); // 앞에 공백이 없는데 대문자일경우
                } else { // 그냥 공백 + 무난
                    sb.append(String.valueOf(s.charAt(i)));
                }
            }
        }


        answer = sb.toString();

        return answer;
    }
}