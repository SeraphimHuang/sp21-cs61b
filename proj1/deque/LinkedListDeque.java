package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

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

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {

        Node cur;

        public LinkedListIterator() {
            cur = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            if (cur != sentinel) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T temp = cur.item;
            cur = cur.next;
            return temp;
        }
    }


    public LinkedListDeque(){
        sentinel = new Node(null, null);
        size = 0;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public void addFirst(T item){
        Node temp = sentinel.next;
        sentinel.next = new Node(item, temp);
        temp.prev = sentinel.next;
        sentinel.next.prev = sentinel;
        size += 1;
    }

    @Override
    public void addLast(T item){
        Node temp = sentinel.prev;
        sentinel.prev = new Node(item, sentinel);
        temp.next = sentinel.prev;
        sentinel.prev.prev = temp;
        size += 1;
    }

    @Override
    public boolean isEmpty(){
        return sentinel.next == sentinel;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        Node cur = sentinel;
        while (cur.next != sentinel){
            cur = cur.next;
            System.out.print(cur.item + " ");
        }
        System.out.println();
    }

    @Override
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

    @Override
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

    @Override
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

    private T getRecursiveHelper(Node cur, int index){
        if (index == 0){
            return cur.item;
        }
        return getRecursiveHelper(cur.next, index -= 1);
    }

    public T getRecursive(int index){
        if (index >= size){
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);

    }
}
