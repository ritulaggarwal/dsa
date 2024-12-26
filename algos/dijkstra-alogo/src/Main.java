import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> edges = List.of(
                List.of(0,1,4),
                List.of(0,7,8),
                List.of(1,2,8),
                List.of(1,7,11),
                List.of(2,3,7),
                List.of(2,8,2),
                List.of(2,5,4),
                List.of(3,4,9),
                List.of(3,5,14),
                List.of(4,5,10),
                List.of(5,6,2),
                List.of(6,7,1),
                List.of(6,8,6),
                List.of(7,8,7)
        );
        dijkstra(edges, 9,0);
    }

    private static void dijkstra(List<List<Integer>> edges, int vertices, int src) {
        List<iPair>[] graph = new ArrayList[vertices];
        for(int i=0;i<vertices;i++) {
            graph[i] = new ArrayList<>();
        }
        for(List<Integer> edge: edges) {
            graph[edge.get(0)].add(new iPair(edge.get(1), edge.get(2)));
            graph[edge.get(1)].add(new iPair(edge.get(0), edge.get(2)));
        }
        PriorityQueue<WeightAndVertex> pq = new PriorityQueue<>((v1,v2)-> v1.weight-v2.weight);
        int [] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[src]=0;
        pq.offer(new WeightAndVertex(src,0));
        while(!pq.isEmpty()) {
            WeightAndVertex weightAndVertex = pq.poll();
            int vertex = weightAndVertex.vertex;
            for(iPair neighbour: graph[vertex]) {
                if(distance[neighbour.first] > distance[vertex] + neighbour.second) {
                    distance[neighbour.first] = distance[vertex] + neighbour.second;
                    pq.offer(new WeightAndVertex(neighbour.first, distance[neighbour.first]));
                }
            }
        }
        for(int i=0;i<vertices;i++) {
            System.out.println(distance[i]);
        }
    }

    static class WeightAndVertex{
        int weight;
        int vertex;
        public WeightAndVertex(int vertex, int weight) {
            this.weight = weight;
            this.vertex = vertex;
        }
    }

    static class iPair {
        int first;
        int second;
        public iPair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
