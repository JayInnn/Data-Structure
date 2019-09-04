package Chapter5;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/9/4
 * @description:
 * check the algorithm and understand the find-union operations.
 * @result:
 * Printing the parent array, and show the forest.
 *  0   1   2   3   4   5   6   7   8   9   10  11
 * -5   3   0  -3   0   3  -4   0   6   8   6   2
 */
public class EquivalenceClass {
    private int[] parent;

    private static final int defaultSize = 12;

    public EquivalenceClass(int size){
        parent = new int[size];
        for (int i = 0; i < size; i++)
            parent[i] = -1;
    }

    public EquivalenceClass(){
        this(defaultSize);
    }

    public void equivalence(int i, int j){
        if (parent[i] < 0 && parent[j] < 0){
            union(i,j);
        } else if (parent[i] < 0){
            int root = find(j);
            union(i, root);
        }else if (parent[j] < 0){
            int root = find(i);
            union(root, j);
        }else {
            int root1 = find(i);
            int root2 = find(j);
            if (root1 != root2)
                union(root1, root2);
        }
    }

    private int find(int i){
        for (;parent[i] >= 0; i = parent[i])
            ;
        return i;
    }

    private void union(int i, int j){
        int tmp = parent[i] + parent[j];
        if (parent[i] > parent[j]){
            parent[i] = j;
            parent[j] = tmp;
        }else {
            parent[j] = i;
            parent[i] = tmp;
        }
    }

    public void printParent(){
        System.out.println("Printing the parent array, and show the forest.");
        for (int i = 0; i < parent.length; i++){
            if (i > 9)
                System.out.print( " " + i + " ");
            else System.out.print( " " + i + "  ");
        }
        System.out.println();
        for (int entry : parent){
            if (entry >= 0)
                System.out.print(" " + entry + "  ");
            else System.out.print(entry + "  ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        int[][] a = {{0, 4}, {3, 1}, {6, 10}, {8, 9}, {7, 4},
                {6, 8}, {3, 5}, {2, 11}, {11, 0}};
        EquivalenceClass equivalenceClass = new EquivalenceClass();
        for (int i = 0; i < a.length; i++){
            equivalenceClass.equivalence(a[i][0], a[i][1]);
        }
        equivalenceClass.printParent();
    }
}
