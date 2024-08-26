import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder convert = new StringBuilder();
        StringBuilder tube = new StringBuilder();
        
        for (int i = 0; convert.length() <= m * t; i++){
            convert.append(Integer.toString(i, n)); 
            // n진수의 i를 string으로 반환해서 넣어줌.
        }
        
        for (int i = p - 1; tube.length() < t; i += m){
            tube.append(convert.toString().charAt(i));
        }
        
        return tube.toString().toUpperCase();
    }
}