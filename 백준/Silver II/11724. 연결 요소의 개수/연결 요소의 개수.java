import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
 
public class Main {
    
    static int N,M;
    static int [] parents;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Integer> hs = new HashSet<>();
        
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        parents = new int[N+1];
        
        for(int i=1;i<parents.length;i++) parents[i] = i;
        
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            if(find(u)!=find(v)) union(u,v);
                
        }
        
        for(int i=1;i<parents.length;i++) {
            hs.add(find(parents[i]));
        }
        
        System.out.println(hs.size());
    }
    
    public static void union(int a,int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        
        if(aRoot>bRoot) parents[aRoot] = bRoot;
        else parents[bRoot] = aRoot;
    }
    
    public static int find(int a) {
        if(parents[a]==a) return a;
        return parents[a] = find(parents[a]);
    }
 
}