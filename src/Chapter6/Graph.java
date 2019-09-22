package Chapter6;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/9/6
 * @description:
 * Realize the ADT of graph. The data structure of graph is adjacency lists.
 * The most important method is dfs and bfs -- they are the basic graph-ordering method and dfst, bfst is based on them.
 * In addition, the problem of biconnected graph is articulation point and biconnected component.
 * @result:
 */
public class Graph {
    static class Node {
        int vertex;
        Node link;
        Node(int vertex, Node link){
            this.vertex = vertex;
            this.link = link;
        }
        Node(int vertex){
            this(vertex, null);
        }
    }
    private Node[] graph;
    private boolean[] visited;
    private int vSize;

    private LinkedList<Integer> queue = new LinkedList<>();

    private static final int defaultSize = 12;

    public Graph(int size){
        vSize = size;
        graph = new Node[size];
        visited = new boolean[size];
    }

    public Graph(){
        this(defaultSize);
    }

    public void insertVertex(int vertex){
        if (vertex < vSize){
            graph[vertex] = new Node(vertex);
            visited[vertex] = false;
        } else System.out.println("This graph is out of bound!");
    }

    public void insertEdge(int vertex1, int vertex2){
        int[] vertex = {vertex1, vertex2};
        for (int i = 0; i< vertex.length; i++){
            Node tmp = graph[vertex[i]];
            while (tmp.link != null)
                tmp = tmp.link;
            tmp.link = new Node(vertex[1 - i]);
        }
    }

    public void deleteVertex(int vertex){
        while (graph[vertex].link != null){
            Node tmp = graph[vertex].link;
            deleteEdge(vertex, tmp.vertex);
        }
        graph[vertex] = null;
    }

    public void deleteEdge(int vertex1, int vertex2){
        int[] vertex = {vertex1, vertex2};
        for (int i = 0; i < vertex.length; i++){
            Node tmp = graph[vertex[i]];
            Node preTmp = null;
            while (tmp.link != null){
                preTmp = tmp;
                tmp = tmp.link;
                if (tmp.vertex == vertex[1 - i] ){
                    preTmp.link = tmp.link;
                    break;
                }
            }
        }
    }

    public boolean isEmpty(){
        return graph == null;
    }

    public void adjacent(int vertex){
        System.out.print("the adjacent vertex of V(" + vertex  +"): ");
        Node tmp = graph[vertex];
        while (tmp.link != null){
            tmp = tmp.link;
            System.out.print(tmp.vertex + "  ");
        }
        System.out.println();
    }

    public void depthFirstSearch(int vertex){
        System.out.print("depth first search: ");
        initVisited();
        dfs(vertex);
        System.out.println();
    }

    public void breadthFirstSearch(int vertex){
        System.out.print("breadth first search: ");
        initVisited();
        bfs(vertex);
        System.out.println();
    }

    private void dfs(int vertex){
        visited[vertex] = true;
        System.out.print(vertex + "  ");
        for (Node tmp = graph[vertex]; tmp != null; tmp = tmp.link)
            if (!visited[tmp.vertex])
                dfs(tmp.vertex);
    }

    private void bfs(int vertex){
        System.out.print(vertex + "  ");
        queue.offer(vertex);
        visited[vertex] = true;
        while (queue.size() != 0){
            int v = queue.poll();
            for (Node tmp = graph[v]; tmp != null; tmp = tmp.link)
                if (!visited[tmp.vertex]){
                    System.out.print(tmp.vertex + "  ");
                    queue.offer(tmp.vertex);
                    visited[tmp.vertex] = true;
                }
        }
    }

    private void initVisited(){
        for (int i = 0; i < vSize; i++)
            visited[i] = false;
    }

    public void connectedComponent(){
        System.out.print("connected component: ");
        initVisited();
        connected();
        System.out.println();
    }

    private void connected(){
        for (int i = 0; i < vSize; i++){
            if (!visited[i]){
                dfs(i);
                System.out.print("/  ");
            }
        }
    }

    public Graph depthFirstSpanningTree(){
        Graph newGraph = new Graph(vSize);
        initVisited();
        dfst(0, newGraph);
        return newGraph;
    }

    private void dfst(int vertex, Graph newGraph){
        newGraph.insertVertex(vertex);
        visited[vertex] = true;
        for (Node tmp = graph[vertex]; tmp != null; tmp = tmp.link)
            if (!visited[tmp.vertex]){
                dfst(tmp.vertex, newGraph);
                newGraph.insertEdge(vertex, tmp.vertex);
            }
    }

    public Graph breadthFirstSpanningTree(){
        Graph newGraph = new Graph(vSize);
        initVisited();
        bfst(0, newGraph);
        return newGraph;
    }

    private void bfst(int vertex, Graph newGraph){
        queue.offer(vertex);
        visited[vertex] = true;
        newGraph.insertVertex(vertex);
        while (queue.size() != 0){
            int v = queue.poll();
            for (Node tmp = graph[v]; tmp != null; tmp = tmp.link)
                if (!visited[tmp.vertex]){
                    queue.offer(tmp.vertex);
                    visited[tmp.vertex] = true;
                    newGraph.insertVertex(tmp.vertex);
                    newGraph.insertEdge(v, tmp.vertex);
                }
        }
    }

    public void articulationPoint(){
        int[] dfn = new int[vSize];
        int[] low = new int[vSize];
        for (int i = 0; i < vSize; i++)
            dfn[i] = low[i] = -1;
        //dfnlow
        vSize = 0;
        dfnlow(3, -1, dfn, low);
        System.out.println("dfn[]: " + Arrays.toString(dfn));
        System.out.println("low[]: " + Arrays.toString(low));
        //traversing dfn and low
    }

    public void biconnectedComponent(){
        int[] dfn = new int[vSize];
        int[] low = new int[vSize];
        for (int i = 0; i < vSize; i++)
            dfn[i] = low[i] = -1;
        vSize = 0;
        bicon(3, -1, dfn, low);
    }

    private void dfnlow(int u, int v, int[] dfn, int[] low){
        dfn[u] = low[u] = vSize++;
        for (Node tmp = graph[u]; tmp != null; tmp = tmp.link){
            int w = tmp.vertex;
            if (dfn[w] < 0){
                dfnlow(w, u, dfn, low);
                low[u] = low[u] < low[w] ? low[u] : low[w];
            }else if (w != v){
                low[u] = low[u] < dfn[w] ? low[u] : dfn[w];
            }
        }
    }

    private void bicon(int u, int v, int[] dfn, int[] low){
        dfn[u] = low[u] = vSize++;
        for (Node tmp = graph[u]; tmp != null; tmp = tmp.link){
            int w = tmp.vertex;
            if (v != w && dfn[w] < dfn[u]){     //add the back edge
                queue.push(u);
                queue.push(w);
                if (dfn[w] < 0){
                    bicon(w, u, dfn, low);
                    low[u] = low[u] < low[w] ? low[u] : low[w];
                    if (low[w] >= dfn[u]){
                        System.out.print("New biconnected component: ");
                        int x = 0, y = 0;
                        do {
                            x = queue.pop();
                            y = queue.pop();
                            System.out.print(" <" + x + ", " + y + ">");
                        }while (!((y == u) && (x == w)));
                        System.out.println();
                    }
                }else if (w != v){
                    low[u] = low[u] < dfn[w] ? low[u] : dfn[w];
                }
            }
        }
    }

    public void printGraph(){
        System.out.println("Vertically print the graph:");
        for (int i = 0; i < vSize; i++){
            StringBuilder str = new StringBuilder();
            Node tmp = graph[i];
            for (;tmp != null; tmp = tmp.link)
                str.append(tmp.vertex).append("  ");
            System.out.println(str.toString());
        }
    }

    public static void main(String[] args){
        Graph graph = new Graph(8);
        int[] vertex = {0,1,2,3,4,5,6,7};
        int[][] edge = {{0,1},{0,2},{1,3},{2,3},{4,5},{5,6},{6,7}};
        for (int i = 0; i < vertex.length; i++)
            graph.insertVertex(vertex[i]);
        for (int i = 0; i < edge.length; i++)
            graph.insertEdge(edge[i][0], edge[i][1]);
        graph.printGraph();
        graph.adjacent(5);
        graph.depthFirstSearch(0);
        graph.breadthFirstSearch(0);
        graph.connectedComponent();
        System.out.print("After deleting the edge -- E<0, 1>.  ");
        graph.deleteEdge(0,1);
        graph.printGraph();
        System.out.print("After deleting the vertex -- V<0>.  ");
        graph.deleteVertex(0);
        graph.printGraph();

        System.out.println("==============================================");
        Graph graph1 = new Graph(8);
        int[] vertex1 = {0,1,2,3,4,5,6,7};
        int[][] edge1 = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,7},{5,7},{6,7}};
        for (int i = 0; i < vertex1.length; i++)
            graph1.insertVertex(vertex1[i]);
        for (int i = 0; i < edge1.length; i++)
            graph1.insertEdge(edge1[i][0], edge1[i][1]);
        graph1.printGraph();
        System.out.print("depth first spanning tree. ");
        graph1.depthFirstSpanningTree().printGraph();
        System.out.print("breadth first spanning tree. ");
        graph1.breadthFirstSpanningTree().printGraph();

        System.out.println("==============================================");
        Graph graph2 = new Graph(10);
        int[] vertex2 = {0,1,2,3,4,5,6,7,8,9};
        int[][] edge2 = {{0,1},{1,2},{2,4},{3,4},{1,3},{3,5},{5,6},{5,7},{6,7},{7,9},{7,8}};
        for (int i = 0; i < vertex2.length; i++)
            graph2.insertVertex(vertex2[i]);
        for (int i = 0; i < edge2.length; i++)
            graph2.insertEdge(edge2[i][0], edge2[i][1]);
        graph2.printGraph();
        graph2.articulationPoint();
        graph2.biconnectedComponent();
    }
}
