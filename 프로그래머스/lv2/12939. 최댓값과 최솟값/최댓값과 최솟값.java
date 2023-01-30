import java.util.*;
class Solution {
    public String solution(String s) {
        String answer = "";

        StringTokenizer st = new StringTokenizer(s);
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (true) {
            String str = "";
            try {
                str = st.nextToken();
            } catch (NoSuchElementException e) {
                break;
            }
            int n = Integer.parseInt(str);

            arrayList.add(n);
        }

        Collections.sort(arrayList);

        answer = arrayList.get(0) + " " + arrayList.get(arrayList.size() - 1);
        return answer;
    }

    /*public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("-1 -2 -3 -4"));
    }*/
}