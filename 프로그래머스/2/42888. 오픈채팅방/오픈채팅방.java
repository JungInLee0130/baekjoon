import java.util.*;
class Solution {
    static int recordLen;
    public String[] solution(String[] record) {
        recordLen = record.length; // 10만 이하
        
        // 유저아이디, 닉네임
        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < recordLen; i++){
            String[] sp = record[i].split(" ");
            if (sp[0].equals("Enter") || sp[0].equals("Change")){
                hashMap.put(sp[1], sp[2]);                                          
            }
        }
        
        
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < recordLen; i++){
            String[] sp = record[i].split(" ");
            
            if (sp[0].equals("Enter")){
                // +들어왔습니다.
                list.add(hashMap.get(sp[1]) + "님이 들어왔습니다.");
            }
            if (sp[0].equals("Leave")){
                // +나갔습니다.
                list.add(hashMap.get(sp[1]) + "님이 나갔습니다.");
            }
        }
        
        String[] answer = new String[list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        
        
        return answer;
    }
}