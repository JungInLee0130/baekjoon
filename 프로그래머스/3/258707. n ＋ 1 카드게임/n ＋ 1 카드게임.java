import java.util.*;

class Solution {
    static Set<Integer> original, additional;
    public int solution(int coin, int[] cards) {
        int answer = 0;
        
        int len = cards.length;
        
        original = new HashSet<>();
        additional = new HashSet<>();
        
        // n + 1을 맞춰야함 최대한.
        
        // 카드 2장을 뽑고 coin을 통해 가져올수있음.
        // 1. 무조건 2장씩은 뽑아야함.
        // 2. 무조건 n + 1을 맞춰서 내야함.
        
        // 처음에 n / 3장 뽑아야함.
        
        // cards는 최대 1000개
        
        // 그럼 경우의수 따져야하는데.
        
        // 조합으로 하면 무조건 시간초과임.
        
        // 아, 순서대로 뽑는듯
        
        // 처음 카드는 n/3까지임.
        
        int idx = len / 3;
        
        for (int i = 0; i < idx; i++){
            original.add(cards[i]);
        }
        
        int target = len + 1;
        
        while (true){
            answer++;
            if (idx >= len){
                break;
            }
            
            additional.add(cards[idx]);
            additional.add(cards[idx + 1]);
            idx += 2;
            
            boolean flag = false;
            // target = n + 1 // 합 맞춰야함.
            for(int i : original){
                if (original.contains(target - i)){
                    original.remove(i);
                    original.remove(target - i);
                    flag = true;
                    break;
                }
            }
            
            if (!flag){
                if (coin > 0){
                    for(int i : original){
                        if (additional.contains(target - i)){
                            original.remove(i);
                            additional.remove(target-i);
                            --coin;
                            flag = true;
                            break;
                        }
                    }
                }
            }
            
            if (!flag){
                if (coin > 1){
                    for(int i : additional){
                        if (additional.contains(target - i)){
                            additional.remove(i);
                            additional.remove(target - i);
                            coin -= 2;
                            flag = true;
                            break;
                        }
                    }
                }
            }
            if (!flag){
                break;
            }
        }
        
        return answer;
    }
}