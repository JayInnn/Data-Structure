package Chapter6;

import java.util.Collections;
import java.util.LinkedList;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/9/7
 * @description: minimum cost spanning tree -- Kruskal & Prim
 * @result:
 */
public class MinCostSpanningTree {
    //the data structure -- adjacency matrices represents graph
    static class Edge implements Comparable<Edge> {
        int lVertex;
        int rVertex;
        int weight;
        Edge(int leftVertex, int rightVertex, int weight){
            this.lVertex = leftVertex;
            this.rVertex = rightVertex;
            this.weight = weight;
        }

        public int compareTo(Edge e){
            return e.weight > this.weight ? -1 : 1;
        }
    }

    public static int[][] kruskal(int[][] graph){
        //sorted the edge
        LinkedList<Edge> sortedEdge = new LinkedList<>();
        for (int i = 0; i < graph.length; i++)
            for (int j = i + 1; j < graph.length; j++)
                if (graph[i][j] > 0){
                    Edge edge = new Edge(i, j, graph[i][j]);
                    sortedEdge.add(edge);
                }
        Collections.sort(sortedEdge);

        //add Edge and judge tree
        int[][] result = new int[graph.length][graph.length];
        int[] parent = new int[graph.length];
        for (int i = 0; i < graph.length; i++)
            parent[i] = -1;
        int m = 0, n = 0, edgeNum = 0;
        for (Edge edge : sortedEdge){
            m = find(parent, edge.lVertex);
            n = find(parent, edge.rVertex);
            if (m != n){
                parent[edge.rVertex] = edge.lVertex;
                result[edge.lVertex][edge.rVertex] = result[edge.rVertex][edge.lVertex] = edge.weight;
                edgeNum++;
            }
            if (edgeNum == graph.length - 1) break;
        }
        return result;
    }

    private static int find(int[] parent, int vertex){
        while (parent[vertex] > -1)
            vertex = parent[vertex];
        return vertex;
    }

    public static int[][] prim(int vertex, int[][] graph){
        int vertexNum = graph.length;
        int[][] result = new int[vertexNum][vertexNum];
        LinkedList<Integer> list = new LinkedList<>();
        list.add(vertex);

        while (list.size() < vertexNum){
            int leftVertex = 0, rightVertex = 0, weight = Integer.MAX_VALUE;
            for (Integer v : list){
                for (int i = 0; i < vertexNum; i++){
                    if (!list.contains(i)){
                        if (graph[v][i] > 0 && graph[v][i] < weight){
                            leftVertex = v;
                            rightVertex = i;
                            weight = graph[v][i];
                        }
                    }
                }
            }
            if (weight == Integer.MAX_VALUE){
                System.out.println("No spanning tree! The ST contains fewer than n vertex");
                break;
            }
            list.add(rightVertex);
            result[leftVertex][rightVertex] = result[rightVertex][leftVertex] = weight;
        }
        return result;
    }

    public static void printTree(int[][] tree){
        for (int i = 0; i < tree.length; i++){
            for (int j = 0; j < tree.length; j++){
                if (tree[i][j] >= 10)
                    System.out.print("   " + tree[i][j]);
                else
                    System.out.print("    " + tree[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        int[][] graph = new int[][]{
                { 0, 28,  0,  0,  0, 10,  0},
                {28,  0, 16,  0,  0,  0, 14},
                { 0,  16,  0, 12,  0,  0,  0},
                { 0,  0, 12,  0, 22,  0, 18},
                { 0,  0,  0, 22,  0, 25, 24},
                {10,  0,  0,  0, 25,  0,  0},
                { 0, 14,  0, 18, 24,  0,  0}
        };
        int[][] primST = prim(0, graph);
        printTree(primST);
        System.out.println("===========================================");
        int[][] kruST = kruskal(graph);
        printTree(kruST);
    }
}
