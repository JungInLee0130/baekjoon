import java.util.*;

class Solution {
    static HashMap<String,Integer> map;

    public static void combination(String order, StringBuilder sb, int idx, int cnt, int n) {
        if (cnt == n) {
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
            return;
        }

        for (int i = idx; i < order.length(); i++) {
            sb.append(order.charAt(i));
            // i : order index
            // cnt : sb index
            combination(order, sb, i + 1, cnt + 1, n);
            sb.delete(cnt, cnt + 1);
        }
    }

    public ArrayList<String> solution(String[] orders, int[] course) {
        ArrayList<String> answer = new ArrayList<>();

        for (int i = 0; i < orders.length; i++) {
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            orders[i] = String.valueOf(arr);
        }

        // 자릿수에 대해
        for (int courseLength : course) {
            map = new HashMap<>();

            int max = Integer.MIN_VALUE;

            // orders 중에
            for (String order : orders) {
                StringBuilder sb = new StringBuilder();

                if (courseLength <= order.length()) {
                    combination(order,sb,0,0,courseLength);
                }
            }

            // countLength 중에 가장 큰값을 arraylist에 넣는다
            for (Map.Entry<String,Integer> entry: map.entrySet()) {
                max = Math.max(max, entry.getValue());
            }

            for (Map.Entry<String,Integer> entry: map.entrySet()) {
                if (max >= 2 && entry.getValue() == max) {
                    answer.add(entry.getKey());
                }
            }
        }

        Collections.sort(answer);

        return answer;
    }
}