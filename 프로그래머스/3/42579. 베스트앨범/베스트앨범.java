import java.util.*;
class Solution {
    static int N;
    public int[] solution(String[] genres, int[] plays) {
        N = genres.length;
        
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> sumPlays = new HashMap<>();
        
        int k = 0;
        for (int i = 0; i < N; i++){
            if (!map.containsKey(genres[i])){
                map.put(genres[i], k++);
            }
            sumPlays.put(genres[i], sumPlays.getOrDefault(genres[i],0) + plays[i]);
        }
        
        int[][] musics = new int[N][4];
        
        for (int i = 0; i < N; i++){
            musics[i][0] = i;
            musics[i][1] = map.get(genres[i]);
            musics[i][2] = plays[i];
            musics[i][3] = sumPlays.get(genres[i]);
        }
        
        Arrays.sort(musics, new Comparator<>(){
            @Override
            public int compare(int[] music1, int[] music2){
                if (music2[3] == music1[3]){ // 전체 재생수가 같으면
                    if (music1[1] == music2[1]){ // 장르가 같으면
                        if (music2[2] == music1[2]){ // 단일 재생수가 같으면
                            return music1[0] - music2[0]; // 고유번호 : 오름차순
                        }
                        return music2[2] - music1[2]; // 단일 재생수 : 내림차순
                    }
                    return music1[1] - music2[1]; // 장르순 : 오름차순
                }
                return music2[3] - music1[3]; // 전체 재생수
            }
        });
        
        ArrayList<Integer> answer = new ArrayList<>();
        int idx = musics[0][1]; // genre
        answer.add(musics[0][0]);
        int cnt = 1;
        for (int i = 1; i < N; i++){
            if (cnt == 0){ // 0임
                if (musics[i - 1][1] != musics[i][1]){ // 새로들어옴
                    answer.add(musics[i][0]); // 고유번호 넣음
                    cnt++;
                }
            }
            else if (cnt == 1){ // 1개이고
                if (musics[i - 1][1] == musics[i][1]){ // 고유번호 같음
                    answer.add(musics[i][0]); // 고유번호
                }
                else{ // 다름
                    i--;
                }
                cnt = 0;
            }
        }
        
        int[] real = new int[answer.size()];
        k = 0;
        for (Integer e : answer){
            real[k++] = e;
        }
        
        
        return real;
    }
}