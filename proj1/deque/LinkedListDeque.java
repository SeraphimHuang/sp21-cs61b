package deque;

public class LinkedListDeque<T> {
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        public Node(T i, Node n){
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new Node(null, null);
        size = 0;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(T item){
        Node temp = sentinel.next;
        sentinel.next = new Node(item, temp);
        temp.prev = sentinel.next;
        sentinel.next.prev = sentinel;
        size += 1;
    }

    public void addLast(T item){
        Node temp = sentinel.prev;
        sentinel.prev = new Node(item, sentinel);
        temp.next = sentinel.prev;
        sentinel.prev.prev = temp;
        size += 1;
    }

    public boolean isEmpty(){
        return sentinel.next == sentinel;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node cur = sentinel;
        while (cur.next != sentinel){
            cur = cur.next;
            System.out.print(cur.item + " ");
        }
        System.out.println();
    }

    public T removeFirst(){

        if (isEmpty()){
            return null;
        }
        Node temp = sentinel.next;
        sentinel.next = temp.next;
        temp.next.prev = sentinel;
        size -= 1;
        return temp.item;
    }

    public T removeLast(){

        if (isEmpty()){
            return null;
        }
        Node temp = sentinel.prev;
        sentinel.prev = temp.prev;
        temp.prev.next = sentinel;
        size -= 1;
        return temp.item;
    }

    public T get(int index){
        Node cur = sentinel;
        while (cur.next != sentinel){
            cur = cur.next;
            if (index == 0){
                return cur.item;
            }
            index -= 1;
        }
        return null;
    }

    private T getRecursiveHelper(Node cur, int cur_index, int index){
        if (cur_index >= size){
            return null;
        }
        if (index < 0){
            return null;
        }
        if (cur_index == index){
            return cur.item;
        }
        return getRecursiveHelper(cur.next, cur_index + 1, index);
    }

    public T getRecursive(int index){
        return getRecursiveHelper(sentinel.next, 0, 0);
    }

}
