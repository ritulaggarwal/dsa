import java.util.Arrays;

public class Main {

    private final static  int INF = 99999;

    public static void main(String[] args) {
        int[][] edges = {
                {0,1,5},
                {0,3,10},
                {1,2,3},
                {2,3,1}
        };
        int vertices = 4;
        int[][] ans = calculateShortestDistance(edges, vertices);
        for(int i=0;i<vertices;i++) {
            System.out.println(Arrays.toString(ans[i]));
        }
    }



    private static int[][] calculateShortestDistance(int[][] edges, int vertices) {
        int [][] distance = new int[vertices][vertices];
        int [][] graph = new int[vertices][vertices];
        for(int i=0;i<vertices;i++) {
            Arrays.fill(distance[i], INF);
        }
        for(int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            distance[u][v] = weight;
        }
        for(int i=0;i<vertices;i++) {
            distance[i][i]=0;
        }
        for(int k=0;k<vertices;k++) {
            for(int i=0;i<vertices;i++) {
                for(int j=0;j<vertices;j++) {
                    if(distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
        for(int i=0;i<vertices;i++) {
            for(int j=0;j<vertices;j++) {
                if(distance[i][i]<0) {
                    return new int[][] {{-1}};
                }
            }
        }
        return distance;
    }
}