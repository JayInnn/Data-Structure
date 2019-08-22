package Chapter4.uti;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/8/14
 * @description:
 * In this class, I try to explain how to deal with circular linked list.
 * @result:
 * 0 5  1  4  2  3  3  2  4  1
 * the size of circular list: 10
 * 0  1  4  2  3  3  2  4  1
 * the size of circular list: 9
 */
public class CircularListUtility {
    private static class Node{
        public int value;
        public Node next;

        public Node(int value, Node next){
            this.value = value;
            this.next = next;
        }
    }
    private Node cList;

    public CircularListUtility(){}

    public void insert(int v){
        if (cList == null){
            Node tmp = new Node(v, null);
            tmp.next = tmp;
            cList = tmp;
        }else {
            Node tmp = new Node(v, null);
            Node p = cList;
            Node q = cList.next;
            p.next = tmp;
            tmp.next = q;
            cList = tmp;
        }
    }

    public void delete(int v){
        if (cList != null){
            Node tmp = cList.next;
            Node pre = cList;
            do {
                if (tmp.value == v){
                    if (tmp.next == tmp){
                        cList = null;
                        break;
                    }
                    if (tmp == cList){
                        cList = pre;
                        pre.next = tmp.next;
                        tmp = pre.next;
                    }else {
                        pre.next = tmp.next;
                        tmp = pre.next;
                    }
                }else {
                    tmp = tmp.next;
                    pre = pre.next;
                }
            }while (tmp != cList.next);
        }else {
            System.out.println("This circular list is empty! Cannnot delete the element!");
        }
    }

    public int getSize(){
        int size = 0;
        if (cList != null){
            Node tmp = cList;
            do {
                size++;
                tmp = tmp.next;
            }while (tmp != cList);
        }
        return size;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        if (cList != null){
            Node tmp = cList.next;
            do {
                str.append(tmp.value).append("  ");
                tmp = tmp.next;
            }while (tmp != cList.next);
        }
        return str.toString();
    }

    public static void main(String[] args){
        CircularListUtility cirList = new CircularListUtility();
        for (int i = 0; i < 5; i++){
            cirList.insert(i);
            cirList.insert(5-i);
        }
        System.out.println(cirList);
        System.out.println("the size of circular list: " + cirList.getSize());
        cirList.delete(5);
        System.out.println(cirList);
        System.out.println("the size of circular list: " + cirList.getSize());
    }
}
