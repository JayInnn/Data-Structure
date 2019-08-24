package Chapter4;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/8/23
 * @description:
 * Realizing the equivalence relation.
 * @result:
 * Vertically print the sequence of array :
 * 0  11  4
 * 1  3
 * 2  11
 * 3  5  1
 * 4  7  0
 * 5  3
 * 6  8  10
 * 7  4
 * 8  6  9
 * 9  8
 * 10  6
 * 11  0  2
 * =======================
 * New class: 0  11  4  7  2
 * New class: 1  3  5
 * New class: 6  8  10  9
 */
public class EquivalenceRelation {
    public static class Node{
        public int value;
        public Node next;
        public Node(int value, Node next){
            this.value = value;
            this.next = next;
        }
    }
    public Node[] seq;
    public Boolean[] out;

    private static final int defaultSize = 12;

    public EquivalenceRelation(int size){
        seq = new Node[size];
        out = new Boolean[size];
        for (int i = 0; i < size; i++){
            seq[i] = new Node(i, null);
            out[i] = true;
        }
    }

    public EquivalenceRelation(){
        this(defaultSize);
    }

    public void put(int[][] a){
        insert(a);
    }

    public void put(int i, int j){
        int[][] a = {{i,j}};
        insert(a);
    }

    private void insert(int[][] a){
        for (int i = 0; i < a.length; i++){
            Node p = new Node(a[i][1], null);
            p.next = seq[a[i][0]].next;
            seq[a[i][0]].next = p;

            Node q = new Node(a[i][0], null);
            q.next = seq[a[i][1]].next;
            seq[a[i][1]].next = q;
        }
    }

    public void equivalence(){
        System.out.println("=======================");
        Node stack = null;
        for (int i = 0; i < seq.length; i++){
            if (out[i]){
                StringBuilder str = new StringBuilder("New class: ");
                Node tmp = seq[i];
                out[i] = false;
                str.append(tmp.value).append("  ");
                for (;;){
                    while (tmp != null){
                        if (out[tmp.value]){
                            str.append(tmp.value).append("  ");
                            out[tmp.value] = false;
                            Node p = tmp.next;
                            tmp.next = stack;
                            stack = tmp;
                            tmp = p;
                        }else {
                            tmp = tmp.next;
                        }
                    }
                    if (stack == null)
                        break;
                    tmp = seq[stack.value];
                    stack = stack.next;
                }
                System.out.println(str.toString());
            }

        }
    }

    public void printSeq(){
        for (int i = 0; i < seq.length; i ++){
            StringBuilder str = new StringBuilder();
            Node tmp = seq[i];
            for (;tmp != null; tmp = tmp.next)
                str.append(tmp.value).append("  ");
            System.out.println(str.toString());
        }
    }

    public static void main(String[] args){
        int[][] a = {{0, 4}, {3, 1}, {6, 10}, {8, 9}, {7, 4},
                      {6, 8}, {3, 5}, {2, 11}, {11, 0}};
        EquivalenceRelation equivalenceRelation = new EquivalenceRelation();
        equivalenceRelation.put(a);
        System.out.println("Vertically print the sequence of array :");
        equivalenceRelation.printSeq();
        equivalenceRelation.equivalence();
    }


}
