class Solution {
     public int solution(String s) {
        int answer = s.length();
        int count = 1; // 처음에 자신 포함
        for (int i = 1; i <= s.length()/2; i++) {
            StringBuilder result = new StringBuilder(); // cnt
            String base = s.substring(0, i); // 시작
            for (int j = i; j <= s.length(); j+= i) { // + cnt
                int endIdx = Math.min(j + i, s.length()); // 그냥 인덱스가 최종길이 못넘게 설정
                String compare = s.substring(j, endIdx); // j에서 endIndex까지 자르고 비교
                if (base.equals(compare)){
                    count++;
                } else{ // 다른경우 : 지금까지 했던거 모두 더해줌.
                    if (count >= 2) {
                        result.append(count);
                    }
                    result.append(base);
                    base = compare;
                    count = 1;
                }
            }
            result.append(base); // 마지막 더해줌
            answer = Math.min(answer, result.length());
        }

        return answer;
    }
}