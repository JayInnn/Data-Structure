package Chapter4;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/8/13
 * @description:
 * This class just is used to understand how to construct node by inner class.
 * I try to elegantly write the method of 'delete', but I can't. Maybe, I will
 * do better later:)
 * @result:
 * ======>0  1  2  3  4  5  6  7  8  9
 * ======>0  1  2  3  4  6  7  8  9
 * ======>0  1  2  3  4  7  8  9
 * ======>0  1  2  3  4  8  9
 * ======>0  1  2  3  4  9
 * ======>0  1  2  3  4
 */
public class NaturalNumber {
    private static class Node {
        int value;
        Node next;
        Node(int value, Node next){
            this.value = value;
            this.next = next;
        }
    }
    private Node first;

    public NaturalNumber(){

    }

    public void insert(int v){
        if(first == null){
            first = new Node(v, null);
        }else {
            Node tmp = first;
            while (tmp.next != null){
                tmp = tmp.next;
            }
            tmp.next = new Node(v, null);
        }
    }

    public void delete(int v){
        if(first != null){
            Node tmp = first;
            Node preTmp = null;
            while(tmp.next != null){
                if(tmp.value == v){
                    tmp.value = tmp.next.value;
                    tmp.next = tmp.next.next;
                }else {
                    preTmp = tmp;
                    tmp = tmp.next;
                }
            }
            if(preTmp == null){
                if (tmp.value == v)
                    first = null;
            }else {
                if (tmp.value == v)
                    preTmp.next = null;
            }
        }else {
            System.out.println("There is no element to delete!");
        }
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        Node tmp = first;
        for(;tmp != null; tmp = tmp.next)
            str.append(tmp.value).append("  ");
        return str.toString();
    }

    public static void main(String[] args){
        NaturalNumber naturalNumber = new NaturalNumber();
        for(int i = 0; i < 10; i++){
            naturalNumber.insert(i);
        }
        System.out.println("======>" + naturalNumber);
        for(int i = 5; i < 10; i++){
            naturalNumber.delete(i);
            System.out.println("======>" + naturalNumber);
        }
    }
}
