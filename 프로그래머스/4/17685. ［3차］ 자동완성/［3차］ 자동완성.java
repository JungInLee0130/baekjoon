import java.util.*;

/*

2. 트라이 버전

내가 알고있는 기존 트라이랑 다르게 endOfWord 부분이 처음 방문하면 모두 true로,
반복방문하면 false로 변한다.

따라서 해당 노드까지 입력할수있는 최소횟수를 알수있다.

여기서도 마찬가지로 문자가 포함되는 경우와 포함되지않는 경우로 나뉘는데,

엄밀히 if문으로 나뉘는 건아니고 반복문을 탈출하는 과정이 다르다.

1. 포함하는 경우

query의 length에 먼저 도달해서 while문을 탈출한다.

2. 포함하지않는 경우
isLeafNode = true에 의해 탈출된다.

따라서 word.... 이 예제같은경우

3 4 4 4 를 만족하여

15가 출력된다.
*/
class Solution {
    
    static class Trie{
        boolean isleafNode = true;
        Trie[] subTrie = new Trie[26];
        
        void insert(String key){
            int index = 0;
            Trie trie;
            
            if (this.subTrie[charToNumber(key.charAt(index))] == null){
                trie = this.subTrie[charToNumber(key.charAt(index))] = new Trie();
            }
            else{
                trie = this.subTrie[charToNumber(key.charAt(index))];
                trie.isleafNode = false;
            }
            
            index++;
            
            while (index < key.length()){
                int next = charToNumber(key.charAt(index));
                
                if (trie.subTrie[next] == null){
                    trie.subTrie[next] = new Trie();
                }
                else{
                    trie.subTrie[next].isleafNode = false;    
                }
                trie = trie.subTrie[next];
                index++;
            }
        }
        
        
        int query(String key){
            int sameCharCount = 1, index = 0;
            Trie trie = this.subTrie[charToNumber(key.charAt(index))];
            index++;
            
            while(index < key.length()){
                
                int next = charToNumber(key.charAt(index));
                
                if(trie.isleafNode) break;
                
                sameCharCount++;
                trie = trie.subTrie[next];
                index++;
            }
            
            return sameCharCount;
        }
        
        int charToNumber(char c){
            return c - 'a';
        }
    }
    public int solution(String[] words) {
        int answer = 0;
        
        Arrays.sort(words);
        
        int[] counts = new int[words.length];
        
        Trie trie = new Trie();
        
        for (int i = 0; i < words.length; i++){
            trie.insert(words[i]);
        }
        
        for (int i = 0; i < words.length; i++){
            answer += trie.query(words[i]);
        }
        
        
        return answer;
    }
    
  
}