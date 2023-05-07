import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
         HashMap<String, String> map = new HashMap<>(); // 입차시간 대입
        TreeMap<String, Integer> feeMap = new TreeMap<>(); // 요금 대입
        for (int i = 0; i < records.length; i++) {
            // feeMap에 대입 -> 차량번호 저장
            feeMap.put(records[i].split(" ")[1], 0);
        }

        for (int i = 0; i < records.length; i++) {
            String[] rec = records[i].split(" "); // 시각 차량번호 내역

            if (map.containsKey(rec[1])) { // 차량번호를 담고있으면
                String[] in = map.get(rec[1]).split(":"); // 입차시간
                String[] out = rec[0].split(":"); // 출차시간

                int inhour = Integer.parseInt(in[0]);
                int inmin = Integer.parseInt(in[1]);
                int outhour = Integer.parseInt(out[0]);
                int outmin = Integer.parseInt(out[1]);

                int time = (outhour - inhour) * 60 + outmin - inmin;
                map.remove(rec[1]);// 입차기록 삭제

                feeMap.replace(rec[1], feeMap.get(rec[1]) + time);
            }
            else{ // 담고있지않으면
                map.put(rec[1], rec[0]);
            }
        }

        // map에 기록이 남아있는경우 -> 23:59 계산필요
        for (Map.Entry<String,String> entry : map.entrySet()) {
            String num = entry.getKey();
            String[] in = entry.getValue().split(":");

            int inhour = Integer.parseInt(in[0]);
            int inmin = Integer.parseInt(in[1]);

            int time = (23 - inhour) * 60 + (59 - inmin);

            feeMap.replace(num, feeMap.get(num) + time);
        }

        int[] price = new int[feeMap.size()];

        // 트리맵은 유니코드로 자동정렬
        int index = 0;
        for (Map.Entry<String,Integer> entry: feeMap.entrySet()) {
            int time = entry.getValue();

            price[index] += fees[1]; // 기본요금 더함
            if (time > fees[0]) { // 기본시간을 넘음
                price[index] += (int)Math.ceil((time - fees[0]) / (double)fees[2]) * fees[3];
            }
            index++;
        }

        return price;
    }
}