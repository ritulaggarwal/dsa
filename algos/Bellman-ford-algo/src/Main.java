import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//TC: O(V*E)
public class Main {
    public static void main(String[] args) {
        int V = 5;
        int[][] edges = new int[][] {
                {1, 3, 2},
                {4, 3, -1},
                {2, 4, 1},
                {1, 2, 1},
                {0, 1, 5}
        };
        int src =0;
        int[] ans = calculateShortestDistanceFromSource(edges,src,V);
        System.out.println(Arrays.toString(ans));
    }

    private static int[] calculateShortestDistanceFromSource(int[][] edges, int src, int vertices) {
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[src]=0;
        for(int i=0;i<vertices;i++) {
            for(int[] edge: edges) {
                int u = edge[0];
                int v = edge[1];
                int weight = edge[2];
                if(distance[u]!= Integer.MAX_VALUE && distance[u]+weight < distance[v]) {
                    // If we can do relaxation at V, it means there is negative weight cycle.
                    if(i==vertices-1) {
                        return new int[]{-1};
                    }
                    distance[v]=distance[u] + weight;
                }
            }
        }

        return distance;
    }
}