import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Set<String> reportSet = new HashSet<>();
        
        for(String element : report) {
            reportSet.add(element);
        }
        
        Map<String, Integer> reportMap = new HashMap<>();
        Iterator<String> iter = reportSet.iterator();
        while (iter.hasNext()) {
            String element = iter.next();
            StringTokenizer st = new StringTokenizer(element);
            String key = st.nextToken();
            String value = st.nextToken();  // 신고당한사람
            // count + 1
            reportMap.put(value, reportMap.getOrDefault(value, 0) + 1);
        }
        
        List<String> inValidPeople = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : reportMap.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            if (value >= k) {
                inValidPeople.add(key);
            }
        }
        
        
        Map<String, Set<String>> whoReportMap = new HashMap<>();
        iter = reportSet.iterator();    // iter index가 저장되나봄. 초기화해야함.
        while (iter.hasNext()) {
            String element = iter.next();
            StringTokenizer st = new StringTokenizer(element);
            String key = st.nextToken();
            String value = st.nextToken();
            
            whoReportMap.putIfAbsent(key, new HashSet<>());
            Set<String> reportedPeopleSet = whoReportMap.get(key);
            reportedPeopleSet.add(value);
            whoReportMap.put(key, reportedPeopleSet);    
        }
        
        Map<String, Integer> mailCountMap = new HashMap<>();
        for (String invalidPerson : inValidPeople) {
            for(Map.Entry<String, Set<String>> entry : whoReportMap.entrySet()) {
                String key = entry.getKey();    // 신고한사람
                Set<String> values = entry.getValue(); // 신고 목록
                
                if (values.contains(invalidPerson)) {   // 신고목록에 invalidPerson 포함
                    // 신고한 사람은 count + 1 
                    mailCountMap.put(key, mailCountMap.getOrDefault(key, 0) + 1); 
                }
            }
        }
        
         
        for(Map.Entry<String, Integer> entry : mailCountMap.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();

            System.out.println(key + " :" + value);
        }
        
        
        int[] result = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            String key = id_list[i];
            
            result[i] = mailCountMap.getOrDefault(key, 0);
        }
        
        return result;
    }
}