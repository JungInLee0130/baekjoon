import java.util.*;
class Solution {
    static int N, M;
    public int solution(String begin, String target, String[] words) {
        M = words.length;
        N = begin.length();
        
        boolean isCan = false;
        for (int i = 0; i < M; i++){
            // 도달할수있는지 여부
            if (target.equals(words[i])){
                isCan = true;
                break;
            }
        }
        
        if (!isCan) return 0;
        
        visited = new boolean[M];
        
        dfs(begin, target, words, 0);

        //System.out.println(min);
        if (min == Integer.MAX_VALUE){
            return 0;
        }

        return min;
    }
    static int min = Integer.MAX_VALUE;
    static boolean[] visited;
    private static void dfs(String begin, String target, String[] words, int count){
        // begin == target : min <- 최소값 넣어줌
        if (begin.equals(target)){
            min = Math.min(min, count);
            return;
        }
        
        for (int i = 0; i < M; i++){
            int notSameCount = 0;
            
            if (visited[i]) continue;
            
            for (int j = 0; j < N; j++){
                if (begin.charAt(j) != words[i].charAt(j)){
                    notSameCount++;
                }
            }

            // 변환 가능 : count + 1 후 dfs
            if (notSameCount == 1){
                visited[i] = true;
                //System.out.println(words[i]);
                dfs(words[i], target, words, count + 1);
                visited[i] = false;
            }
        }
    }
}