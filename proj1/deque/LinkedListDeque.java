package deque;

public class LinkedListDeque<T>{
    public class Node{
        public Node prev;
        public Node next;
        public T item;
        public Node(Node p, int i, Node n){
            prev = p;
            item = i;
            next = n;
        }
    }
    public int size;
    public Node sentinel;
    public LinkedListDeque(){
        size = 0;
        sentinel = Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(T item){
        size += 1;
        Node a = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = a;
        sentinel.next = a;
    }

    public void addLast(T item){
        size += 1;
        Node a = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = a;
        sentinel.prev = a;
    }

    public boolean isEmpty(){
        return (this.size() == 0);
    }

    public int size(){
        return size;
    }

}
