import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class Solution {
  public int[] solution(String today, String[] terms, String[] privacies) {
        ArrayList<Integer> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < terms.length; i++) {
            StringTokenizer st = new StringTokenizer(terms[i]);
            String term = st.nextToken();
            int month = Integer.parseInt(st.nextToken()); // 달수
            map.put(term, month); // 대입
        }
        // today: 현재날짜
        // terms: 조건당 시간(달)
        // privacies: 기준날짜, 조건
        // privacies의 날짜에 시간을 더했을때 현재날짜보다 작으면 모두 폐기 -> 모아서 리턴
        for (int i = 0; i < privacies.length; i++) {
            StringTokenizer st = new StringTokenizer(privacies[i]);
            String d = st.nextToken();
            String a = st.nextToken();
            // 기준날짜, 조건
            try{
                Date date = dateFormat.parse(d); // 날짜로 변환
                calendar.setTime(date); // 기준날짜 기준
                calendar.add(Calendar.MONTH, map.get(a)); // 조건 대입한거를 더한 월
                calendar.add(Calendar.DATE, -1 );
                if (calendar.get(Calendar.DATE) >= 28){
                    calendar.set(Calendar.DATE, 28);
                }
                String str = dateFormat.format(calendar.getTime());
                //System.out.println("cur:" + str);
                if(str.compareTo(today) < 0) { // +면 파기
                    dates.add(i + 1);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }


        return dates.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}