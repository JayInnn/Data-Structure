package Chapter6;

import java.util.LinkedList;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/9/7
 * @description:
 * @result:
 */
public class MinCostSpanningTree {
    //the data structure -- adjacency matrices represents graph
    public void kruskal(){

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
            System.out.println("=======>" + leftVertex + "=======>" + rightVertex + "=======>" + weight);
        }
        return result;
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
        for (int i = 0; i < primST.length; i++){
            for (int j = 0; j < primST.length; j++){
                if (primST[i][j] >= 10)
                    System.out.print("   " + primST[i][j]);
                else
                    System.out.print("    " + primST[i][j]);
            }
            System.out.println();
        }
    }
}
