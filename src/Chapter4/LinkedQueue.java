package Chapter4;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/8/13
 * @description:
 * Attention: if the queue is empty and execute the method -- peek, it will return -1 to represent the exception.
 * @result:
 * front--0  1  2  3  4  --rear
 * the value of peeking: 0
 * after peeking: front--1  2  3  4  --rear
 */
public class LinkedQueue {
    private static class Node{
        int value;
        Node next;
        Node(int value, Node next){
            this.value = value;
            this.next = next;
        }
    }
    private Node rear;
    private Node front;
    private int size = 0;

    public LinkedQueue(){

    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int getSize(){
        return size;
    }

    public void offer(int v){
        if (size == 0){
            rear = new Node(v, null);
            front = rear;
            size++;
        }else {
            rear.next = new Node(v, null);;
            rear = rear.next;
            size++;
        }
    }

    public int peek(){
        int value = 0;
        if (size == 0)
            value = -1;
        else if(size == 1){
            value = front.value;
            front = null;
            rear = null;
            size--;
        }else {
            value = front.value;
            Node tmp = front;
            front = front.next;
            tmp.next = null; // help gc
            size--;
        }
        return value;
    }

    public String toString(){
        StringBuilder str = new StringBuilder("front--");
        Node tmp = front;
        for (;tmp != null; tmp = tmp.next)
            str.append(tmp.value).append("  ");
        str.append("--rear");
        return str.toString();
    }

    public static void main(String[] args){
        LinkedQueue linkedQueue = new LinkedQueue();
        for(int i = 0; i < 5; i++){
            linkedQueue.offer(i);
            System.out.println("isEmpty: " + linkedQueue.isEmpty() + ";  getSize: " + linkedQueue.getSize());
        }
        System.out.println(linkedQueue);
        System.out.println("the value of peeking: " + linkedQueue.peek());
        System.out.println("after peeking: " + linkedQueue);
    }
}
