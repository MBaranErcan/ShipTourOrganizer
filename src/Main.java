//-----------------------------------------------------
// Title: Main class
// Author: Mustafa Baran Ercan
// ID: 28810555206
// Section: 1
// Homework: 1
// Description: This class is the main class of the second question.
//-----------------------------------------------------
import java.util.*;

public class Main {
    public static void main(String[] args) {

            
            Scanner scanner = new Scanner(System.in);
            
            int N = scanner.nextInt();  // - N represents the number of islands we have (vertices).
            int M = scanner.nextInt();  // - M represents the number of connections between the islands (edges).
            
            Graph graph = new Graph(N);
            
            // - Get edges (roads) from the user.
            for (int i = 0; i < M; i++) {
                int v = scanner.nextInt();
                int u = scanner.nextInt();
                
                checkValidVertex(graph, v, u);  // - Check if the vertices are valid.
                graph.addEdge(v, u);            // - If they are valid, add them to the graph.
            }
            
            // - Get the source and destination vertices.
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            
            List<Integer> cycle = new LinkedList<>();   // - List to store the cytcle that includes src and des.
            
            cycle = graph.getCycle(source, destination); // - Calls getCycle method to find if there is cycle with src and des.
            
            printResult(cycle); // - To print the result.
    
            
    } 
        
    // - Checks if the input vertex is valid or not.
    public static void checkValidVertex(Graph graph, int N, int M) {
        if ((N > graph.V()) || (N <= 0)) {
            System.out.println("There is no such island with the name: " + N); 
            System.exit(0);
        }
        if ((M > graph.V()) || (M <= 0)) {
            System.out.println("There is no such island with the name: " + M); 
            System.exit(0);
        }
        if (N == M) {
            System.out.println("Source and destination cannot be the same: (" + N + " = " + M + ")"); 
            System.exit(0);
        }      
    }
    
    public static void printResult(List<Integer> list) {    // - Print method.
        int[] result = new int[list.size()-1];              // - Creates an array with the same size as list
        for (int i = 0; i < list.size()-1; i++)             // - Copies the elements to array
            result[i] = list.get(i); 
        
        Arrays.sort(result);                                // - Orders in lexicographic order.

        for (int i : result) {
            System.out.print(i + " ");                      // - Prints the result.
        }
    }
    
}