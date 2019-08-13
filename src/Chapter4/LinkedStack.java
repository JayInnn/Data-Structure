package Chapter4;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/8/13
 * @description:
 * Just finish linked stack. One more thing, in the method -- pop, when the stack is empty, it will
 * return "-1" to represent the unsupported operation. But you can throw an exception to replace it.
 * @result:
 * =======>0  1  2  3  4
 * the value of poping: 0
 * After poping:1  2  3  4
 * the value of poping: 1
 * After poping:2  3  4
 */
public class LinkedStack {
    private static class Node{
        int value;
        Node next;
        Node(int value, Node next){
            this.value = value;
            this.next = next;
        }
    }
    private Node top;
    private int size;

    public LinkedStack(){

    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int getSize(){
        return size;
    }

    public void push(int v){
        if(top == null){
            top = new Node(v, null);
            size++;
        } else {
            Node tmp = top;
            while (tmp.next != null){
                tmp = tmp.next;
            }
            tmp.next = new Node(v, null);
            size++;
        }
    }

    public int pop(){
        int value = top == null ? -1 : top.value;
        top = top == null ? null : top.next;
        return value;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        Node tmp = top;
        for(;tmp != null; tmp = tmp.next)
            str.append(tmp.value).append("  ");
        return str.toString();
    }

    public static void main(String[] args){
        LinkedStack linkedStack = new LinkedStack();
        for (int i = 0; i < 5; i++){
            linkedStack.push(i);
            System.out.println("isEmpty: " + linkedStack.isEmpty() + " getSize:" + linkedStack.getSize());
        }
        System.out.println("=======>" + linkedStack);
        System.out.println("the value of poping: " + linkedStack.pop());
        System.out.println("After poping:" + linkedStack);
        System.out.println("the value of poping: " + linkedStack.pop());
        System.out.println("After poping:" + linkedStack);
    }
}
