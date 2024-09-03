import java.util.*;

class Solution {
    static int N;
    public int solution(int[] arr) {
        N = arr.length;
                
        int lcm = 1;
        for (int i = 0; i < N; i++){
            int gcd = getGcd(lcm, arr[i]);
            
            // arr[i] / gcd * arr[i + 1] / gcd * gcd
            // = arr[i] * arr[i + 1] / gcd
            lcm = lcm * arr[i] / gcd; 
        }
        
        return lcm;
    }
    
    public int getGcd(int n1, int n2){
        // a > b
        int a = Math.max(n1,n2);
        int b = Math.min(n1,n2);
        
        int r = 0;
        while (b != 0){ // 나누는 수가 0이 아닐때 까지
            r = a % b; // b
            // b -> a
            a = b;
            b = r;
        }
        
        return a;
    }
}