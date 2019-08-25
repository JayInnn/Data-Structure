package Chapter5;

import java.util.LinkedList;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/8/24
 * @description:
 * In this BinTree class, I try to construct a complete binary tree to understand some methods.
 * The most important thing is that I use LinkedList class as stack and queue.
 * @result:
 * The hight of tree: 4
 * preorder: 1  2  4  8  9  5  10  11  3  6  12  13  7  14  15
 * inorder: 8  4  9  2  10  5  11  1  12  6  13  3  14  7  15
 * postorder: 8  9  4  10  11  5  2  12  13  6  14  15  7  3  1
 * iter_inorder: 8  4  9  2  10  5  11  1  12  6  13  3  14  7  15
 * level_order: 1  2  3  4  5  6  7  8  9  10  11  12  13  14  15
 */
public class BinTree<T> {
    static final class TreeNode<T>{
        T value;
        TreeNode<T> left, right;
        TreeNode(T v, TreeNode<T> left, TreeNode<T> right){
            this.value = v;
            this.left = left;
            this.right = right;
        }
        TreeNode(T v){
            this(v, null, null);
        }
    }

    private TreeNode<T> root;
    public BinTree(){}

    public boolean isEmpty(){
        return root == null;
    }

    //form the complete binary tree
    public void insert(T value){
        if (root == null){
            root = new TreeNode<T>(value);
        }else {
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (queue.size() != 0){
                TreeNode tmp = queue.poll();
                if (tmp.left != null && tmp.right != null){
                    queue.offer(tmp.left);
                    queue.offer(tmp.right);
                }else if (tmp.left == null){
                    tmp.left = new TreeNode<T>(value);
                    break;
                }else {
                    tmp.right = new TreeNode<T>(value);
                    break;
                }
            }
        }
    }

    public TreeNode<T> getRoot(){
        return root;
    }

    //return the left child tree of bt
    public TreeNode lChild(TreeNode<T> bt){
        return bt.left;
    }

    public T data(TreeNode<T> bt){
        return bt.value;
    }

    //return the right child tree of bt
    public TreeNode rChild(TreeNode<T> bt){
        return bt.right;
    }

    public int high(TreeNode bt){
        if (bt != null){
            int left = high(bt.left) + 1;
            int right = high(bt.right) + 1;
            return left > right ? left : right;
        }
        return 0;
    }

    public void inorder(TreeNode bt){
        if (bt != null){
            inorder(bt.left);
            System.out.print(bt.value + "  ");
            inorder(bt.right);
        }
    }

    public void preorder(TreeNode bt){
        if (bt != null){
            System.out.print(bt.value + "  ");
            preorder(bt.left);
            preorder(bt.right);
        }
    }

    public void postorder(TreeNode bt){
        if (bt != null){
            postorder(bt.left);
            postorder(bt.right);
            System.out.print(bt.value + "  ");
        }
    }

    public void iter_inorder(TreeNode<T> bt){
        LinkedList<TreeNode<T>> linkedList = new LinkedList<>();
        for (;;){
            for (; bt != null; bt = bt.left)
                linkedList.push(bt);
            bt = linkedList.pop();
            System.out.print(bt.value + "  ");
            if (linkedList.size() == 0 && bt.right == null)
                break;
            bt = bt.right;
        }
    }

    public void level_order(TreeNode<T> bt){
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        if (bt != null)
            linkedList.offer(bt);
        while (linkedList.size() != 0){
            TreeNode tmp = linkedList.poll();
            System.out.print(tmp.value + "  ");
            if (tmp.left != null)
                linkedList.offer(tmp.left);
            if (tmp.right != null)
                linkedList.offer(tmp.right);
        }
    }

    public TreeNode<T> copy(TreeNode<T> bt){
        TreeNode<T> tmp;
        if (bt != null){
            tmp = new TreeNode<T>(bt.value);
            tmp.left = copy(bt.left);
            tmp.right = copy(bt.right);
            return tmp;
        }
        return null;
    }

    public static void main(String[] args){
        BinTree<Integer> binTree = new BinTree<>();
        for (int i = 1; i < 16; i++){
            binTree.insert(i);
        }
        System.out.println("The hight of tree: " + binTree.high(binTree.getRoot()));

        System.out.print("preorder: ");
        binTree.preorder(binTree.getRoot());
        System.out.println();

        System.out.print("inorder: ");
        binTree.inorder(binTree.getRoot());
        System.out.println();

        System.out.print("postorder: ");
        binTree.postorder(binTree.getRoot());
        System.out.println();

        System.out.print("iter_inorder: ");
        binTree.iter_inorder(binTree.getRoot());
        System.out.println();

        System.out.print("level_order: ");
        binTree.level_order(binTree.getRoot());
        System.out.println();
    }
}
