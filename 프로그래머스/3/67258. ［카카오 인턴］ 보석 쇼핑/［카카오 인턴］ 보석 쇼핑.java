import java.util.*;
class Solution {
    static Set<String> originalSet = new HashSet<>();
    static Set<String> curSet = new HashSet<>();
    static int N, length;
    public int[] solution(String[] gems) {
        int startIdx = 0;
        int endIdx = 0;
        
        N = gems.length;
        for (int i = 0; i < N; i++){
            originalSet.add(gems[i]);
        }
        
        int[] answer = new int[2];
        
        Deque<String> deque = new LinkedList<>();
        Map<String, Integer> jewelMap = new HashMap<>();
        
        length = N;
        
        for (int i = 0; i < N; i++){
            String element = gems[i];
            
            deque.addLast(element);
            curSet.add(element);
            
            // 카운트 수 세려면 map 써야함.
            jewelMap.put(element, jewelMap.getOrDefault(element, 0) + 1);
            
            // 중복되는 요소 계속 제거
            while (jewelMap.get(gems[startIdx]) > 1){
                jewelMap.put(gems[startIdx], jewelMap.get(gems[startIdx]) - 1);
                startIdx++;
            }

            // 최솟값 갱신해주고 당시 인덱스를 계속 저장할 필요가 있음
            if (curSet.size() == originalSet.size() 
               && (i - startIdx) < length){
                length = i - startIdx;
                answer[0] = startIdx + 1;
                answer[1] = i + 1;
            }

        }
        
        
        return answer;
    }
}