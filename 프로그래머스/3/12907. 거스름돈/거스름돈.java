class Solution {
    static int cnt;
    public int solution(int n, int[] money) {
        combi(n, money, 0, 0);
        return cnt;
    }
    private static void combi(int total, int[] money, int idx, int curPrice){
        if (curPrice > total) return;
        if (curPrice == total){
            cnt++;
            return;
        }
        for (int i = idx; i < money.length; i++){
            combi(total, money, i, curPrice + money[i]);
        }
    }
}