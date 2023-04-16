//-----------------------------------------------------
// Title: Graph class
// Author: Mustafa Baran Ercan
// ID: 28810555206
// Section: 1
// Homework: 1
// Description: This class is the Undirectional Graph implementation for the second question.
//-----------------------------------------------------
import java.util.*;

    public class Graph {                                    // - Graph object implementation.
        private final int V;                                // - Number of vertices (islands) in the graph.
        private List<Integer>[] adjList;                    // - Adjacency list for each element.
        private int E = 0;                                  // - Number of edges in the graph.

        public Graph(int V) {                               // - Constructor to create a graph with V vertices..
            this.V = V;                                         
            adjList = new List[V+1];                        // - Initialize adjacency list for each vertex.
            for (int i = 1; i < V + 1; i++) {               // - (V + 1) instead of (V) because airport indexes start from 1 not 0.
                adjList[i] = new ArrayList<Integer>();
            }
        }

    public void addEdge(int v, int w) {                     // - Method to add an undirected edge between vertices v and w.
            adjList[v].add(w);                              // - Add w to v's adjacency list.
            adjList[w].add(v);                              // - And vice versa.
            E++;                                            // - Increase the E by 1.
        }

        public int V() {                                    // - Method to get the number of vertices in the graph
            return V;
        }
        
    public List<Integer> getCycle(int src, int des) {
        boolean[] marked = new boolean[V+1];                        // - Array to keep track of the visited vertices.
        int[] edgeTo = new int[V+1];                                // - Array to keep track of parent of each vertex.
        List<Integer> cycle = new ArrayList<>();                    // - Cycle list to store the vertices.
        for (int i = 1; i < V; i++) {                               // - Loops through all vertices;
            if (!marked[i] && dfs(i, marked, edgeTo, cycle, src, des)) { // -  If the vertex in not marked, perform DFS from that vertex.
                if (cycle.contains(src) && cycle.contains(des)) {   // - Checks if the cycle contains both source and destination vertices.
                    return cycle;                                   // - If includes, returns the cycle.
                } else {
                    cycle.clear();                                  // - If not, clear the cycle in order to continue searching for another cycle
                }
            }
        }
        return null;                                                // - Return null if no cycle which includes src and des is found. 
    }
    
    /***
     * dfs method which returns true if the graph has a cycle with the wanted src and des vertices. The dfs method takes in five arguments:
     * @param u = current vertex.
     * @param marked = boolean array to keep track of if a vertex is visited.
     * @param edgeTo = int array to keep track of the parent of each vertex.
     * @param cycle = list to store the vertices in the cycle
     * @param src = source.
     * @param des = destination.
     */
    private boolean dfs(int u, boolean[] marked, int[] edgeTo, List<Integer> cycle, int src, int des) { 
        marked[u] = true;           // - Marks the current vertex.
        for (int v : adjList[u]) {  // - Loops all adjacent vertices.
            if (!marked[v]) {       // - If adj vertex is not marked before.
                edgeTo[v] = u;      // - Sets its edgeTo value to current vertex.
                if (dfs(v, marked, edgeTo, cycle, src, des)) {  // - Recursive DFS does the work.
                    return true;    // - If it succeeds, returns true (there is cycle with src and des).
                }
            } else if (v != edgeTo[u]) {    // - If the adj vertex has been visited and has not edgeTo of the current vertex:
                int cur = u;                // - It means we have found a cycle!
                while (cur != v) {          
                    cycle.add(cur);
                    cur = edgeTo[cur];      // - So, adds all vertices in that cycle to our list.
                }
                cycle.add(v);               // - Do not forget to add the endpoints of the cycle (for example 1 and 4 in the example in the pdf).
                cycle.add(u);
                if (cycle.contains(src) && cycle.contains(des)) { // Finally , checks if the cycle found contains source and destination vertices.
                    return true; // - If it includes, returns true.
                } else {
                    cycle.clear(); // - If not, recursively keeps searching for another cycle that includes.
                }
            }
        }   // - If the loop is done w/o finding a cycle, returns false of course.
        return false;
    }
}
