package Chapter8;

/**
 * @author: yinwenjie
 * @email: 475660997@qq.com
 * @date: 2019/10/14
 * @description:
 * @result:
 */
public class SymbolTable<K, V> {
    static class Node<K, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K,V> next){
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    private Node<K, V>[] table;
    private int capacity;

    private static final int defaultCapacity = 16;

    @SuppressWarnings("unchecked")
    public SymbolTable(int capacity){
        table = (Node<K, V>[]) new Node[capacity];
        this.capacity = capacity;
    }

    public SymbolTable(){
        this(defaultCapacity);
    }

    private int hash(K key){
        if (key instanceof Integer)
            return (Integer) key;
        else if (key instanceof String){
            int result = 0;
            for (int i = 0; i < ((String) key).length(); i++)
                result += (int)((String) key).charAt(i);
            return result;
        }else return -1;
    }

    public boolean isIn(K key){
        boolean result = false;
        for (Node<K, V> tmp = table[hash(key) % capacity]; tmp != null; tmp = tmp.next)
            if (tmp.key == key){
                result = true;
                break;
            }
        return result;
    }

    public V find(K key){
        V value = null;
        for (Node<K, V> tmp = table[hash(key) % capacity]; tmp != null; tmp = tmp.next)
            if (tmp.key == key){
                value = tmp.value;
                break;
            }
        return value;
    }

    public void insert(K key, V value){
        int hash = hash(key);
        Node<K, V> tmp = table[hash % capacity];
        if (tmp == null){
            table[hash % capacity] = new Node<K, V>(hash, key, value, null);
        }else {
            for (; tmp != null; tmp = tmp.next){
                if (tmp.key == key){
                    tmp.value = value;
                    break;
                }else if (tmp.next == null){
                    tmp.next = new Node<K, V>(hash, key, value, null);
                    break;
                }
            }
        }
    }

    public void delete(K key){
        Node<K, V> tmp = table[hash(key) % capacity];
        if (tmp == null)
            return;
        if (tmp.key == key){
            table[hash(key) % capacity] = tmp.next;
        }else {
            while (tmp.next != null){
                if (tmp.next.key == key){
                    tmp.next = tmp.next.next;
                    break;
                }
                tmp = tmp.next;
            }
        }
    }

    public void printTable(){
        System.out.println("Vertically print the table:");
        for (int i = 0; i < capacity; i++){
            StringBuilder str = new StringBuilder();
            Node tmp = table[i];
            for (;tmp != null; tmp = tmp.next)
                str.append("<").append(tmp.key).append(", ").append(tmp.value).append(">").append("  ==>  ");
            System.out.println(str.toString());
        }
    }

    public static void main(String[] args){
        SymbolTable<String, String> symbolTable = new SymbolTable<>(6);
        String[] values = {"a","b","c","d","e","f","g","h","i","j"};
        for (int i = 0; i < 10; i++)
            symbolTable.insert(values[i], values[i]);
        symbolTable.printTable();
        System.out.println("a isIN: " + symbolTable.isIn("a"));
        System.out.println("find b: " + symbolTable.find("b"));
        symbolTable.delete("f");
        symbolTable.printTable();
    }
}
