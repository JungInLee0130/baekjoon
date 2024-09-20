import java.util.*;
class Solution {
    static long max;
    static List<Long> nums;
    static List<Character> opers;
    static char[] operations = {'+', '-', '*'};
    public long solution(String expression) {
        init(expression);
        dfs(0);
        return max;
    }
    static boolean[] visited;
    static void init(String expression){
        max = 0;
        nums = new ArrayList<>();
        opers = new ArrayList<>();

        op = new char[3];
        visited = new boolean[3];
        int idx = 0;
        
        // 분리하기
        for (int i = 0; i < expression.length(); i++){
            char ch = expression.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*'){
                opers.add(ch);
                nums.add(Long.parseLong(expression.substring(idx, i)));
                idx = i + 1;
            }
        }
        nums.add(Long.parseLong(expression.substring(idx)));
    }
    static char[] op;
    private static void dfs(int cnt){
        if (cnt == 3){
            // cal에서 한번에 계산
            max = Math.max(Math.abs(cal()), max);
            return;
        }
        
        for (int i = 0; i < 3; i++){
            if (visited[i]) continue;
            visited[i] = true;
            op[cnt] = operations[i]; 
            dfs(cnt + 1);
            visited[i] = false;
        }
    }
    
    private static long cal(){
        List<Long> tmpNums = new ArrayList<>(nums);
        List<Character> tmpOpers = new ArrayList<>(opers);
        
        while (tmpOpers.size() > 0){
            int maxPriority = 0;
            for (int i = 0; i < tmpOpers.size(); i++){
                maxPriority = Math.max(getPriority(tmpOpers.get(i)), maxPriority);
            }
            
            for (int i = 0; i < tmpOpers.size(); i++){
                if (getPriority(tmpOpers.get(i)) == maxPriority){
                    tmpNums.add(i, 
                                calc(tmpNums.remove(i), tmpNums.remove(i),
                                    tmpOpers.remove(i)));
                    break;
                }
            }
        }
        
        return tmpNums.get(0);
    }
    
    private static long calc(long num1, long num2, char o){
        if (o == '+'){
            return num1 + num2;
        }
        else if (o == '-'){
            return num1 - num2;
        }
        else{
            return num1 * num2;
        }
    }
    
    private static int getPriority(char o){
        int priority;
        
        if (o == op[0]){
            priority = 2;
        }
        else if (o == op[1]){
            priority = 1;
        }
        else {
            priority = 0;
        }
        
        return priority;
    }
}