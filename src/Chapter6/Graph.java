package Chapter6;

import java.util.LinkedList;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/9/6
 * @description:
 * Realize the ADT of graph. The data structure of graph is adjacency lists.
 * The most important method is dfs and bfs -- they are the basic graph-ordering method.
 * @result:
 * Vertically print the graph:
 * 0  1  2
 * 1  0  3
 * 2  0  3
 * 3  1  2
 * 4  5
 * 5  4  6
 * 6  5  7
 * 7  6
 * depth first search: 0  1  3  2
 * breadth first search: 0  1  2  3
 * connected component: 0  1  3  2  /  4  5  6  7  /
 * After deleting the edge -- E<0, 1>.  Vertically print the graph:
 * 0  2
 * 1  3
 * 2  0  3
 * 3  1  2
 * 4  5
 * 5  4  6
 * 6  5  7
 * 7  6
 * After deleting the vertex -- V<0>.  Vertically print the graph:
 * 1  3
 * 2  3
 * 3  1  2
 * 4  5
 * 5  4  6
 * 6  5  7
 * 7  6
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
        Node tmp = graph[vertex];
        while (tmp.link != null){
            tmp = tmp.link;
            System.out.print(tmp + "  ");
        }
    }

    public void dfs(int vertex){
        visited[vertex] = true;
        System.out.print(vertex + "  ");
        for (Node tmp = graph[vertex]; tmp != null; tmp = tmp.link)
            if (!visited[tmp.vertex])
                dfs(tmp.vertex);
    }

    public void initVisited(){
        for (int i = 0; i < vSize; i++)
            visited[i] = false;
    }

    public void bfs(int vertex){
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

    public void connected(){
        for (int i = 0; i < vSize; i++){
            if (!visited[i]){
                dfs(i);
                System.out.print("/  ");
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

        System.out.print("depth first search: ");
        graph.dfs(0);
        System.out.println();

        graph.initVisited();

        System.out.print("breadth first search: ");
        graph.bfs(0);
        System.out.println();

        graph.initVisited();

        System.out.print("connected component: ");
        graph.connected();
        System.out.println();

        System.out.print("After deleting the edge -- E<0, 1>.  ");
        graph.deleteEdge(0,1);
        graph.printGraph();
        System.out.print("After deleting the vertex -- V<0>.  ");
        graph.deleteVertex(0);
        graph.printGraph();
    }
}
