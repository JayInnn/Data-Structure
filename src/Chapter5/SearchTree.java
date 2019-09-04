package Chapter5;

import java.util.LinkedList;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/8/29
 * @description:
 * illustrate the ADT of binary search tree.
 * @result:
 * The binary search tree: 40  20  60  10  30  50  70  45  55  52
 * delete a node: 40  20  55  10  30  50  70  45  52
 */
public class SearchTree {
    static final class TreeNode {
        int value;
        TreeNode left, right;
        TreeNode(int v, TreeNode left, TreeNode right){
            this.value = v;
            this.left = left;
            this.right = right;
        }
        TreeNode(int v){
            this(v, null, null);
        }
    }
    private TreeNode root;

    public SearchTree(){}

    public TreeNode recursive_search(TreeNode tree, int key){
        if (tree == null)
            return null;
        if (tree.value == key)
            return tree;
        if (tree.value >  key)
            return recursive_search(tree.left, key);
        return recursive_search(tree.right, key);
    }

    public TreeNode iterator_search(TreeNode tree, int key){
        while (tree != null){
            if (tree .value == key)
                return tree;
            if (tree.value < key)
                tree = tree.right;
            else tree = tree.left;
        }
        return null;
    }

    public void insert(int key){
        TreeNode newNode = new TreeNode(key);
        if (root == null){
            root = newNode;
            return;
        }
        TreeNode tmp = modified_search(root, key);
        if (tmp != null){
            if (tmp.value < key)
                tmp.right = newNode;
            else tmp.left = newNode;
        }
    }

    private TreeNode modified_search(TreeNode tree, int key){
        if (tree.value < key)
            return tree.right == null ? tree : modified_search(tree.right, key);
        if (tree.value > key)
            return tree.left == null ? tree : modified_search(tree.left, key);
        return null;
    }

    public void delete(int key){
        if (root == null){
            System.out.println("The binary search tree is empty. Cannot delete the key!");
            return;
        }
        TreeNode treeNode = recursive_search(root, key);
        if (treeNode != null)
            deleteNode(treeNode);
        else System.out.println("Cannot find this key!");
    }

    private void deleteNode(TreeNode tree){
        if (tree.left != null && tree.right != null){
            //find the max value and replace
            TreeNode tmp = tree.left;
            while (tmp.right != null)
                tmp = tmp.right;
            tree.value = tmp.value;
            deleteNode(tmp);
        }else if (tree.left != null){
            TreeNode tmp = tree.left;
            tree.left = null; //help gc
            if (tree == root){
                root = tmp;
                return;
            }
            TreeNode parent = findParent(tree);
            if (parent.left == tree)
                parent.left = tmp;
            else parent.right = tmp;
        }else if (tree.right != null){
            TreeNode tmp = tree.right;
            tree.right = null; //help gc
            if (tree == root){
                root = tmp;
                return;
            }
            TreeNode parent = findParent(tree);
            if (parent.left == tree)
                parent.left = tmp;
            else parent.right = tmp;
        }else {
            //has no child and delete the key. so need parent node
            if (tree == root){
                root = null;
                return;
            }
            TreeNode parent = findParent(tree);
            if (parent.left == tree)
                parent.left = null;
            else parent.right = null;
        }
    }

    private TreeNode findParent(TreeNode tree){
        TreeNode parent = root;
        while (parent.left != tree && parent.right != tree){
            if (parent.value < tree.value)
                parent = parent.right;
            parent = parent.left;
        }
        return parent;
    }

    public int high(TreeNode tree){
        if (tree == null)
            return 0;
        int left = high(tree.left) + 1;
        int right = high(tree.right) + 1;
        return  left> right ? left : right;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        if (root != null)
            linkedList.offer(root);
        while (linkedList.size() != 0){
            TreeNode tmp = linkedList.poll();
            stringBuilder.append(tmp.value).append("  ");
            if (tmp.left != null)
                linkedList.offer(tmp.left);
            if (tmp.right != null)
                linkedList.offer(tmp.right);
        }
        return stringBuilder.toString();
    }

    public static void  main(String[] args){
        SearchTree searchTree = new SearchTree();
        int[] array = {40,60,20,10,30,50,70,45,55,52};
        for (int entry : array)
            searchTree.insert(entry);
        System.out.println("The binary search tree: " + searchTree);
        searchTree.delete(60);
        System.out.println("delete a node: " + searchTree);
    }
}