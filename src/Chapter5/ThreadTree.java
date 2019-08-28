package Chapter5;

import java.util.LinkedList;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/8/27
 * @description:
 * In this class, I build a inorder thread tree, and every time, you insert a node, then the tree will build thread.
 * Through the inorder thread tree, you can easily inorder the tree. If you want to preorder and postorder, you should
 * to build relative tree.
 * @result:
 * the value of root: null
 * inorder: 3  1  4  0  5  2  6
 */
public class ThreadTree<T> {
    static final class TreeNode<T> {
        T value;
        TreeNode<T> left;
        TreeNode<T> right;
        boolean left_thread;
        boolean right_thread;
        TreeNode(T value, TreeNode<T> left, TreeNode<T> right){
            this.value = value;
            this.left = left;
            this.right = right;
        }
        TreeNode(T value){
            this(value, null, null);
        }
    }
    private TreeNode<T> root;

    public ThreadTree(){
        root = new TreeNode<T>(null);
        root.left = root;
        root.left_thread = true;
        root.right = root;
        root.right_thread = false;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void insert(T v){
        TreeNode<T> child = new TreeNode<>(v);
        if (root.left_thread){
            root.left_thread = false;
            root.left = child;
            child.left_thread = true;
            child.right_thread = true;
            child.left = root;
            child.right = root;
            return;
        }
        LinkedList<TreeNode<T>> linkedList = new LinkedList<>();
        linkedList.offer(root.left);
        while (linkedList.size() != 0){
            TreeNode<T> tmp = linkedList.poll();
            if (tmp.left_thread){
                insert_left(tmp, child);
                break;
            }else if (tmp.right_thread){
                insert_right(tmp, child);
                break;
            }else {
                linkedList.offer(tmp.left);
                linkedList.offer(tmp.right);
            }
        }
    }

    private void insert_left(TreeNode<T> parent, TreeNode<T> child){
        child.left = parent.left;
        child.left_thread = parent.left_thread;
        child.right = parent;
        child.right_thread = true;
        parent.left = child;
        parent.left_thread = false;

        if (!child.left_thread){
            TreeNode<T> tmp = inseq(child);
            tmp.right = child;
        }
    }

    private void insert_right(TreeNode<T> parent, TreeNode<T> child){
        child.right = parent.right;
        child.right_thread = parent.right_thread;
        child.left = parent;
        child.left_thread = true;
        parent.right = child;
        parent.right_thread = false;

        if (!child.right_thread){
            TreeNode<T> tmp = insucc(child);
            tmp.left = child;
        }
    }

    private TreeNode<T> inseq(TreeNode<T> tree){
        TreeNode<T> tmp = tree.left;
        if (!tree.left_thread)
            while (!tmp.right_thread)
                tmp = tmp.right;
        return tmp;
    }

    private TreeNode<T> insucc(TreeNode<T> tree){
        TreeNode<T> tmp = tree.right;
        if (!tree.right_thread)
            while (!tmp.left_thread)
                tmp = tmp.left;
        return tmp;
    }

//    public void preorder(TreeNode<T> tree){ }

    public void inorder(TreeNode<T> tree){
        TreeNode<T> tmp = tree;
        for (;;){
            tmp = insucc(tmp);
            if (tmp == tree)
                break;
            System.out.print(tmp.value + "  ");
        }
    }

//    public void postorder(TreeNode<T> tree){}

    public static void main(String[] args){
        ThreadTree<Integer> threadTree = new ThreadTree<>();
        for (int i = 0; i < 7; i++){
            threadTree.insert(i);
        }
        System.out.println("the value of root: " + threadTree.root.value);
        System.out.print("inorder: ");
        threadTree.inorder(threadTree.getRoot());
    }
}
