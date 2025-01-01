import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[][] rates = {{"USD", "INR", "2"}, {"INR", "CHN","3"},
                {"USD", "JPY", "100"}, {"JPY", "CHN", "20"},
                {"USD", "INR", "2"}, {"INR", "CHN","3"},
                {"CHN", "THAI", "200"}};
        String[][] queries = {{"USD", "CHN"}, {"JPY", "THAI"}, {"USD", "AUD"}};
        double[] ans = findAnyCostConversion(rates,queries);
        System.out.println(Arrays.toString(ans));
    }

    // this function will return any possible cost of conversion
    // Not necessarily minimum
    private static double[] findAnyCostConversion(String[][] rates, String[][] queries) {
        Map<String, Map<String,Double>> graph = new HashMap<>();
        for(String[] rate: rates) {
            String from = rate[0];
            String to  = rate[1];
            double value = Double.parseDouble(rate[2]);
            graph.putIfAbsent(from, new HashMap<>());
            graph.putIfAbsent(to, new HashMap<>());

            graph.get(from).put(to, value);
            graph.get(to).put(from, 1.0/value);
        }

        double [] ans = new double[queries.length];
        for(int i=0;i< queries.length;i++) {
            var from = queries[i][0];
            var to = queries[i][1];
            ans[i] = bfs(graph, from,to);
        }
        return ans;
    }

    private static double bfs(Map<String, Map<String, Double>> graph, String start, String target) {
        if(!graph.containsKey(start) || !graph.containsKey(target)) {
            return -1.0;
        }
        Set<String> visited = new HashSet<>();
        Queue<Map.Entry<String, Double>>q = new LinkedList<>();
        q.offer(new AbstractMap.SimpleEntry<>(start, 1.0));
        while(!q.isEmpty()) {
            var currentEntry = q.poll();
            var currentNode = currentEntry.getKey();
            var currentValue = currentEntry.getValue();
            if(visited.contains(currentNode)) {
                continue;
            }
            if(currentNode.equals(target)) {
                return currentValue;
            }
            visited.add(currentNode);
            for(Map.Entry<String, Double> neighbour: graph.getOrDefault(currentNode, new HashMap<>()).entrySet()) {
                if(!visited.contains(neighbour.getKey())) {
                    q.offer(new AbstractMap.SimpleEntry<>(neighbour.getKey(), currentValue* neighbour.getValue()));
                }
            }


        }
        return -1.0;
    }
}