import java.util.*;
class Solution {
    static int n, m;
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        n = skill.length();

        m = skill_trees.length;


        for (int i = 0; i < m; i++){            
            // Skill 문자를 제외한 문자 치환
            // 입출력 예 1 을 예로 들면
            // CBD를 제외한 문자를 모두 지움.
            // 이거 Set으로 추출한거랑 결과 같네.
            String excludeSkill = skill_trees[i].replaceAll("[^" + skill +"]", "");
            
            int elen = excludeSkill.length();

            int idx = 0;
            boolean isOk = true;
            while (idx < n && idx < elen){
                if (skill.charAt(idx) != excludeSkill.charAt(idx)){
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