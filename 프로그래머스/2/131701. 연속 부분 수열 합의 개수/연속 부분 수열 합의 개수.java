import java.util.*;
class Solution {
    static int N;
    static Set<Integer> set;
    public int solution(int[] elements) {
        int answer = 0;
        
        N = elements.length;
        
        set = new HashSet<>();
        
        // 개수 : N개
        for (int i = 1; i <= N; i++){
            // 시작 인덱스
            for (int j = 0; j <= N - 1; j++){
                int k = 0;
                int sum = 0;
                while (k < i){
                    sum += elements[(j + k) % N];
                    k++;
                }
                set.add(sum);
            }
        }
        
        
        return set.size();
    }
}