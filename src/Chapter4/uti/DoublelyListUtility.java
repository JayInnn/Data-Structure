package Chapter4.uti;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/8/22
 * @description:
 * In this class, trying to learn two-way linked-list.
 * @result:
 * 0  2  1  3  2  4  3  5  4  6
 * After deleting: 0  2  1  3  2  4  3  5  4
 */
public class DoublelyListUtility {
    private static class Node{
        public int value;
        public Node pre;
        public Node next;
        public Node(int value, Node pre, Node next){
            this.value = value;
            this.pre = pre;
            this.next = next;
        }
    }
    private Node dList;

    public DoublelyListUtility(){}

    public void insert(int v){
        if (dList == null){
            dList = new Node(v, null, null);
        }else {
            Node tmp = dList;
            while (tmp.next != null)
                tmp = tmp.next;
            Node p = new Node(v, null, null);
            tmp.next = p;
            p.pre = tmp;
        }
    }

    public void delete(int v){
        Node tmp = dList;
        while (tmp != null){
            if (tmp.value == v){
                Node preNode = tmp.pre;
                if (tmp.next == null){
                    preNode.next = null;
                }else {
                    preNode.next = tmp.next;
                    tmp.next.pre = preNode;
                }
                tmp = preNode;
            }
            tmp = tmp.next;
        }
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        Node tmp = dList;
        for(;tmp != null; tmp = tmp.next)
            str.append(tmp.value).append("  ");
        return str.toString();
    }

    public static void main(String[] args){
        DoublelyListUtility dListUtility = new DoublelyListUtility();
        for (int i = 0; i < 5; i++){
            dListUtility.insert(i);
            dListUtility.insert(i+2);
        }
        System.out.println(dListUtility);
        dListUtility.delete(6);
        System.out.println("After deleting: " + dListUtility);
    }
}
