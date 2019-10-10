package Chapter6;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/10/10
 * @description: calculate the earliest time and latest time to get critical activity. As for calculating
 * the latest time, adding another stack to store the topological order.
 * @result:
 * Vertically print the graph:
 * V0: 0(-1)  1(6)  2(4)  3(5)
 * V1: 1(-1)  4(1)
 * V2: 1(-1)  4(1)
 * V3: 1(-1)  5(2)
 * V4: 2(-1)  6(9)  7(7)
 * V5: 1(-1)  7(4)
 * V6: 1(-1)  8(2)
 * V7: 2(-1)  8(4)
 * V8: 2(-1)
 * earliest: [0, 6, 4, 5, 7, 7, 16, 14, 18]
 *   latest: [0, 6, 6, 8, 7, 10, 16, 14, 18]
 * early: [0, 0, 0, 6, 4, 5, 7, 7, 7, 16, 14]
 *  late: [0, 2, 3, 6, 6, 8, 7, 7, 10, 16, 14]
 */
public class AOE {
    static class Node {
        int vertex;
        int duration;
        Node link;
        Node(int vertex, int duration, Node link){
            this.vertex = vertex;
            this.duration = duration;
            this.link = link;
        }
        Node(int vertex, int duration){
            this(vertex, duration, null);
        }
    }

    private Node[] graph;
    private int vertexNum;
    private int edgeNum;

    public AOE(int[] vertex, int[][][] edge){
        vertexNum = vertex.length;
        edgeNum = edge.length;
        graph = new Node[vertex.length];
        for (int i = 0; i < vertex.length; i++)
            graph[i] = new Node(0,-1);
        for (int i = 0; i < edge.length; i++){
            graph[edge[i][0][1]].vertex ++;
            Node tmp = graph[edge[i][0][0]];
            while (tmp.link != null)
                tmp = tmp.link;
            tmp.link = new Node(edge[i][0][1], edge[i][0][2]);
        }
    }

    public void criticalActivity(int[][][] edge){
        int[] early = new int[edgeNum];
        int[] late = new int[edgeNum];
        int[] earliest = new int[vertexNum];
        int[] latest = new int[vertexNum];
        LinkedList<Integer> eStack = new LinkedList<>();
        LinkedList<Integer> lStack = new LinkedList<>();
        //calculate the earliest array
        for (int i = 0; i < graph.length; i++)
            if (graph[i].vertex == 0)
                eStack.push(i);
        while (eStack.size() != 0){
            int vertex = eStack.pop();
            lStack.push(vertex);
            Node tmp = graph[vertex].link;
            for (;tmp != null; tmp = tmp.link){
                if (--graph[tmp.vertex].vertex == 0)
                    eStack.push(tmp.vertex);
                if (earliest[tmp.vertex] < earliest[vertex] + tmp.duration)
                    earliest[tmp.vertex] = earliest[vertex] + tmp.duration;
            }
        }
        //calculate the latest array
        for (int i = 0; i < latest.length; i++)
            latest[i] = earliest[earliest.length-1];
        while (lStack.size() != 0){
            int l = lStack.pop();
            Node tmp = graph[l].link;
            for (; tmp != null; tmp = tmp.link){
                if (latest[l] > latest[tmp.vertex] - tmp.duration)
                    latest[l] = latest[tmp.vertex] - tmp.duration;
            }
        }
        System.out.println("earliest: " + Arrays.toString(earliest));
        System.out.println("  latest: " + Arrays.toString(latest));
        for (int i = 0; i < edgeNum; i++){
            early[i] = earliest[edge[i][0][0]];
            late[i] = latest[edge[i][0][1]] - edge[i][0][2];
        }
        System.out.println("early: " + Arrays.toString(early));
        System.out.println(" late: " + Arrays.toString(late));
    }

    public void printGraph(){
        System.out.println("Vertically print the graph:");
        for (int i = 0; i < graph.length; i++){
            StringBuilder str = new StringBuilder();
            Node tmp = graph[i];
            str.append("V").append(i).append(": ");
            for (;tmp != null; tmp = tmp.link)
                str.append(tmp.vertex).append("(").append(tmp.duration).append(")").append("  ");
            System.out.println(str.toString());
        }
    }

    public static void main(String[] args){
        int[] vertex = {0,1,2,3,4,5,6,7,8};
        int[][][] edge = {
                {{0,1,6}},{{0,2,4}},{{0,3,5}},{{1,4,1}},
                {{2,4,1}},{{3,5,2}},{{4,6,9}},{{4,7,7}},
                {{5,7,4}},{{6,8,2}},{{7,8,4}}
        };
//        int[] vertex = {0,1,2,3,4,5,6,7,8,9};
//        int[][][] edge = {
//                {{0,1,5}},{{0,2,6}},{{1,3,3}},{{2,3,6}},
//                {{2,4,3}},{{3,4,3}},{{3,6,4}},{{3,5,4}},
//                {{4,5,1}},{{4,7,4}},{{5,8,5}},{{7,8,2}},
//                {{6,9,4}},{{8,9,2}}
//        };
        AOE aoe = new AOE(vertex, edge);
        aoe.printGraph();
        aoe.criticalActivity(edge);
    }
}
