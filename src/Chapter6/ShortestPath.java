package Chapter6;

import java.util.Arrays;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/10/9
 * @description: the shortest path -- dijkstra algorithm
 * @result:
 */
public class ShortestPath {

    public static void dijkstra(int[][] graph, int vertex){
        int length = graph.length;
        boolean[] found = new boolean[length];
        int[] distance = new int[length];
        for (int i = 0; i < length; i++){
            found[i] = false;
            distance[i] = graph[vertex][i];
        }

        found[vertex] = true;
        distance[vertex] = 0;
        for (int i = 0; i <  length-2; i++){
            int u = choose(distance, found);   //choose the min-cost vertex
            found[u] = true;
            for (int w = 0; w < length; w++)   //judge that the vertex can arrive to other vertexs and be sure the min-cost
                if (!found[w] && distance[u] + graph[u][w]  < distance[w])
                    distance[w] = distance[u] + graph[u][w];
        }
        System.out.print("The shortest path from single source - vertex(" + vertex + ") to muti-target: ");
        System.out.println(Arrays.toString(distance));
    }

    private static int choose(int[] distance, boolean[] found){
        int minOps = -1, min = Integer.MAX_VALUE;
        for (int i = 0; i < distance.length; i++)
            if (!found[i] && distance[i] < min){
                min = distance[i];
                minOps = i;
            }
        return minOps;
    }

    public static void allcosts(int[][] graph){
        int length = graph.length;
        int[][] distance = new int[length][length];
        for (int i = 0; i < length; i++)
            for (int j = 0; j < length; j++)
                distance[i][j] = graph[i][j];

        for (int k = 0; k < length; k++)
            for (int i = 0; i < length; i++)
                for (int j = 0; j < length; j++)
                    if (distance[i][k] + distance[k][j] < distance[i][j])
                        distance[i][j] = distance[i][k] + distance[k][j];
        System.out.println("The shortest path of all vertexs: ");
        for (int i = 0; i < length; i++){
            for (int j = 0; j < length; j++)
                System.out.print("  " + distance[i][j]);
            System.out.println();
        }
    }

    public static void main(String[] args){
        int[][] graph = new int[][]{
                {    0,   50,   10, 1000,   45, 1000},
                { 1000,    0,   15, 1000,   10, 1000},
                {   20, 1000,    0,   15, 1000, 1000},
                { 1000,   20, 1000,    0,   35, 1000},
                { 1000, 1000,   30, 1000,    0, 1000},
                { 1000, 1000, 1000,    3, 1000,    0},
        };
        dijkstra(graph, 0);
        allcosts(graph);

        int[][] graph1 = new int[][]{
                {  0,    4,  11},
                {  6,    0,   2},
                {  3, 1000,   0}
        };
        allcosts(graph1);
    }
}
