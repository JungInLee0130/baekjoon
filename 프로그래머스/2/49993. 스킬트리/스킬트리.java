import java.util.*;
class Solution {
    static int n, m;
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        n = skill.length();
        
        Set<Character> treeSet = new LinkedHashSet<>();
        
        for (int i = 0; i < n; i++){
            treeSet.add(skill.charAt(i)); // 각 스킬들 담음.
        }
        
        m = skill_trees.length;
        
        List<Set<Character>> setList = new ArrayList<>();
        
        for (int i = 0 ; i < m; i++){
            setList.add(new LinkedHashSet<>());
        }
        
        for (int i = 0; i < m; i++){
            String oneSkill = skill_trees[i];
            int oneSkillLen = oneSkill.length();
            
            for (int j = 0; j < oneSkillLen; j++){
                char charSkill = oneSkill.charAt(j);
                
                if (treeSet.contains(charSkill)){
                    setList.get(i).add(charSkill); // 해당하는 스킬들만 뽑아서 담음.
                }
            }
        }
        
        List<Character> cs = new ArrayList<>();
        
        for(Character c:treeSet){
            cs.add(c);
        }

    
        for (int i = 0; i < m; i++){
            List<Character> es = new ArrayList<>();
            
            for (Character c : setList.get(i)){
                es.add(c);
            }
            
            int idx = 0;
            boolean isOk = true;
            while (idx < cs.size() && idx < es.size()){
                if (cs.get(idx) != es.get(idx)){
                    isOk = false;
                    break;
                }
                idx++;
            }
            if (isOk){
                answer++;
            }
        }
        
        return answer;
    }
}