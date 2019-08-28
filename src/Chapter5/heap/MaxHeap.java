package Chapter5.heap;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/8/27
 * @description:
 * Try to build the max heap and delete the max.
 * @result:
 * After adding the array:16  14  10  8  1  9  3  2  4
 * After adding the value: 16  14  10  8  7  9  3  2  4  1
 * delete the max: 16
 * After deleting: 14  8  10  4  7  9  3  2  1
 */
public class MaxHeap {
    private int[] heap;
    private int heap_size;
    private int array_size;

    public MaxHeap(int size){
        heap = new int[size];
        array_size = size;
    }

    public MaxHeap(){
        this(10);
    }

    public void add(int[] array){
        if ((array.length + heap_size + 1) > array_size){
            System.out.println("Error: out of boundary!");
            return;
        }
        System.arraycopy(array, 0, heap, heap_size + 1, array.length);
        heap_size += array.length;
        buildHeap();
    }

    public void add(int value){
        if ((1 + heap_size + 1) > array_size){
            System.out.println("Error: out of boundary!");
            return;
        }
        heap[++heap_size] = value;
        buildHeap();
    }

    private void buildHeap(){
        for (int i = heap_size / 2; i > 0; i--)
            heapify(i);
    }

    private void heapify(int i){
        int lchild = i * 2;
        int rchild = i * 2 + 1;
        int largest = i;
        if (lchild <= heap_size && heap[i] < heap[lchild])
            largest = lchild;
        if (rchild <= heap_size && heap[largest] < heap[rchild])
            largest = rchild;

        if (largest != i){
            int tmp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = tmp;
            heapify(largest);
        }
    }

    public void delete(){
        System.out.println("delete the max: " + heap[1]);
        heap[1] = heap[heap_size--];
        heapify(1);
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= heap_size; i++)
            str.append(heap[i]).append("  ");
        return str.toString();
    }

    public static void main(String[] args){
        MaxHeap maxHeap = new MaxHeap(20);
        int[] A = {4,1,3,2,16,9,10,14,8};
        maxHeap.add(A);
        System.out.println("After adding the array:" + maxHeap);
        maxHeap.add(7);
        System.out.println("After adding the value: " + maxHeap);
        maxHeap.delete();
        System.out.println("After deleting: " + maxHeap);
    }
}
