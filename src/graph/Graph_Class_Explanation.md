
### Java Graph Class Explanation

#### Core Components

1. **Class `Graph` and its properties**:
    - `private int V`: This variable stores the number of vertices in the graph.
    - `private List<Integer>[] adj`: An array of linked lists to store the edges of the graph. Each element of this array is a linked list that stores the vertices adjacent to a corresponding vertex.

2. **Constructor (`Graph(int v)`)**:
    - This method takes the initial number of vertices `v` and creates an `adj` array with `v` linked lists. Each list will store the adjacent vertices of a specific vertex.

3. **Method `addVertex(Integer vertex)`**:
    - This method adds a new vertex to the graph. If the new vertex is greater than the current number of vertices `V`, it expands the `adj` array and updates `V`.

4. **Method `removeVertex(Integer vertex)`**:
    - This method removes a specific vertex from the graph. It clears all adjacent edges of that vertex and also removes that vertex from the adjacency lists of other vertices.

5. **Method `addEdge(Integer vertex1, Integer vertex2)`**:
    - Adds an edge from `vertex1` to `vertex2`. Before adding, the method ensures that both vertices exist in the graph. If not, it adds new vertices.

6. **Method `removeEdge(Integer vertex1, Integer vertex2)`**:
    - This method removes an edge from `vertex1` to `vertex2` if both vertices exist.

7. **Method `hasEdge(Integer vertex1, Integer vertex2)`**:
    - Checks if there is an edge from `vertex1` to `vertex2`. Returns `true` if there is, `false` if not.

8. **Method `size()`**:
    - Returns the number of vertices in the graph.

9. **Method `isEmpty()`**:
    - Checks if the graph is empty (has no vertices).

10. **Method `iterator()`**:
    - Returns an iterator to go through all the vertices of the graph.

11. **DFS (Depth-First Search)**:
    - **`depthFirstSearch(int v)`**: Starts DFS traversal from vertex `v`.
        - ```java
          for (ElementType element : collection) {
            // Sử dụng element trong khối lệnh này
                }
             such as 
            adj = [[1, 2], // Danh sách kề của đỉnh 0
                [2],    // Danh sách kề của đỉnh 1
                [0, 3], // Danh sách kề của đỉnh 2
                []      // Danh sách kề của đỉnh 3
            ]
          ```
    - **`depthFirstSearchUtil(int v, boolean[] visited)`**: Utility function for DFS, recursively visits all unvisited adjacent vertices of vertex `v`.
    - **`depthFirstSearchAll()`**: Performs DFS traversal through all vertices of the graph, ensuring no vertices are missed, even in a non-connected graph.

12. **Method `printGraph()`**:
    - Prints the structure of the graph, displaying each vertex along with its adjacent vertices.

### Main Section and Operation

- **Initializing the graph**: `Graph graph = new Graph(4);`
    - Creates a graph with 4 vertices (0, 1, 2, 3).

- **Adding edges**:
    - `graph.addEdge(0, 1);`: Adds an edge from vertex 0 to vertex 1.
    - `graph.addEdge(0, 2);`: Adds an edge from vertex 0 to vertex 2.
    - `graph.addEdge(1, 2);`: Adds an edge from vertex 1 to vertex 2.
    - `graph.addEdge(2, 0);`: Adds an edge from vertex 2 to vertex 0.
    - `graph.addEdge(2, 3);`: Adds an edge from vertex 2 to vertex 3.
    - `graph.addEdge(3, 3);`: Adds an edge from vertex 3 to itself.

- **Printing the graph structure**: `graph.printGraph();`
    - Prints the structure of the graph so users can see how the vertices are connected.

- **DFS from vertex 2**: `graph.depthFirstSearch(2);`
    - Performs a DFS traversal starting from vertex 2. It will visit successively the connected vertices from vertex 2 in depth-first order.

### Points to Note
- **Graph resizing**: The graph can automatically expand when adding new vertices that are greater than the current number of vertices.
- **Edge and vertex handling**: All operations of adding/removing vertices or edges are carefully handled to ensure the graph remains consistent.
- **DFS**: This is a typical example of how to traverse a graph using the DFS algorithm, applicable to many different problems such as searching, determining connected components, checking for cycles in the graph, etc.

Feel free to ask if you want more details about any part of the code or have specific questions!
