package Chapter4.uti;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/8/14
 * @description:
 * In this class, adding some utilities of single linked list.
 * @result:
 * 0  1  2  3  4
 * Searching middle value: 2
 * 2  3  4  5  6
 * After concating: 0  1  2  3  4  2  3  4  5  6
 * After inverting: 6  5  4  3  2  4  3  2  1  0
 * Searching middle value: 2
 * Searching the last k element: 6
 * After inverting: 0  1  2  3  4  2  3  4  5  6
 * After sorting: 6  5  4  4  3  3  2  2  1  0
 * After deleteing: 6  5  4  4  2  2  1  0
 * After printing reversely: 0  1  2  2  4  4  5  6
 */
public class LinkedListUtility {
    private static class Node{
        public int value;
        public Node next;

        public Node(int v, Node next){
            this.value = v;
            this.next = next;
        }
    }
    private Node list;

    public LinkedListUtility(){

    }

    public void insert(int v){
        if (list == null){
            list = new Node(v, null);
        }else {
            Node tmp = list;
            while (tmp.next != null)
                tmp = tmp.next;
            tmp.next = new Node(v, null);
        }
    }

    public void invert(){
        if (list == null)
            System.out.println("This linked list is empty, it cannot be inverted!");
        else {
            Node tmp = list;
            Node front = null;
            Node rear;
            while (tmp != null){
                rear = tmp;
                tmp = tmp.next;
                rear.next = front;
                front = rear;
            }
            list = front;
        }
    }

    public void concatList(Node l){
        if (list == null){
            list = l;
        }else {
            Node tmp = list;
            while (tmp.next != null)
                tmp = tmp.next;
            tmp.next = l;
        }
    }

    //search the middle element
    public int searchMid(){
        Node tmp = list;
        Node oneNode = list;
        while (tmp != null && tmp.next != null && tmp.next.next != null){
            oneNode = oneNode.next;
            tmp = tmp.next.next;
        }
        return oneNode == null ? -1 : oneNode.value;
    }

    //search the last k element
    public int find(int k){
        Node tmp = list;
        Node ele = list;
        while( k > 1 && tmp != null){
            tmp = tmp.next;
            k--;
        }
        if (k > 1)
            return -1;

        while (tmp.next != null){
            ele = ele.next;
            tmp = tmp.next;
        }
        return ele.value;
    }

    //sort the list
    public void sort(){
        Node node1 = list;
        while(node1.next != null){
            Node node2 = node1.next;
            while (node2 != null){
                if (node1.value < node2.value){
                    int tmp = node1.value;
                    node1.value = node2.value;
                    node2.value = tmp;
                }
                node2 = node2.next;
            }
            node1 = node1.next;
        }
    }

    //delete the duplicate element
    public void deleteDuplicate(int v){
        Node first = new Node(-1, null);
        first.next = list;
        Node preNode = first;
        Node tmp = list;
        while (tmp != null){
            if (tmp.value == v){
                preNode.next = tmp.next;
                tmp = tmp.next;
            }else {
                preNode = tmp;
                tmp = tmp.next;
            }
        }
        list = first.next;
    }

    //reversely print list by recursive
    public void printReverse(Node l){
        if (l != null){
            printReverse(l.next);
            System.out.print(l.value + "  ");
        }
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        Node tmp = list;
        for(; tmp != null; tmp = tmp.next)
            str.append(tmp.value).append("  ");
        return str.toString();
    }

    public static void main(String[] args){
        LinkedListUtility listUtility = new LinkedListUtility();
        LinkedListUtility listUtility2 = new LinkedListUtility();
        for (int i = 0; i < 5; i++){
            listUtility.insert(i);
            listUtility2.insert(i + 2);
        }
        System.out.println(listUtility);
        System.out.println("Searching middle value: " + listUtility.searchMid());
        System.out.println(listUtility2);
        listUtility.concatList(listUtility2.list);
        System.out.println("After concating: " + listUtility);
        listUtility.invert();
        System.out.println("After inverting: " + listUtility);
        System.out.println("Searching middle value: " + listUtility.searchMid());
        System.out.println("Searching the last k element: " + listUtility.find(10));

        listUtility.invert();
        System.out.println("After inverting: " + listUtility);
        listUtility.sort();
        System.out.println("After sorting: " + listUtility);
        listUtility.deleteDuplicate(3);
        System.out.println("After deleteing: " + listUtility);
        System.out.print("After printing reversely: ");
        listUtility.printReverse(listUtility.list);

    }

}
