package Chapter6;

import java.util.LinkedList;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/10/10
 * @description: get the topological order and judge the network is cycle or not.
 * @result:
 * Vertically print the graph:
 * V0: 0  1  2  3
 * V1: 1  4
 * V2: 1  4  5
 * V3: 1  5  4
 * V4: 3
 * V5: 2
 * topological order: V0  V3  V2  V5  V1  V4
 */
public class AOV {
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

    public AOV(int[] vertex, int[][] edge){
        graph = new Node[vertex.length];
        for (int i = 0; i < vertex.length; i++)
            graph[i] = new Node(0);
        for (int i = 0; i < edge.length; i++){
            graph[edge[i][1]].vertex++;
            Node tmp = graph[edge[i][0]];
            while (tmp.link != null)
                tmp = tmp.link;
            tmp.link = new Node(edge[i][1]);
        }
    }

    public void topSort(){
        LinkedList<Integer> stack = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        int num = 0;
        for (int i = 0; i < graph.length; i++)
            if (graph[i].vertex == 0)
                stack.push(i);

        while (stack.size() != 0){
            int vertex = stack.pop();
            num++;
            stringBuilder.append("V").append(vertex).append("  ");
            Node tmp = graph[vertex].link;
            for (;tmp != null; tmp = tmp.link){
                if (--graph[tmp.vertex].vertex == 0)
                    stack.push(tmp.vertex);
            }
        }
        if (num == graph.length)
            System.out.println("topological order: " + stringBuilder.toString());
        else
            System.out.println("Network has a cycle!");
    }

    public void printGraph(){
        System.out.println("Vertically print the graph:");
        for (int i = 0; i < graph.length; i++){
            StringBuilder str = new StringBuilder();
            Node tmp = graph[i];
            str.append("V").append(i).append(": ");
            for (;tmp != null; tmp = tmp.link)
                str.append(tmp.vertex).append("  ");
            System.out.println(str.toString());
        }
    }

    public static void  main(String[] args){
        int[] vertex = {0,1,2,3,4,5};
        int[][] edge = {{0,1},{0,2},{0,3},{1,4},{2,4},{2,5},{3,5},{3,4}};
        AOV aov = new AOV(vertex, edge);
        aov.printGraph();
        aov.topSort();
    }
}
