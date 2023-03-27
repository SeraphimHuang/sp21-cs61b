package deque;

public class LinkedListDeque<T>{
    public class Node{
        public Node prev;
        public Node next;
        public T item;
        public Node(Node p, T i, Node n){
            prev = p;
            item = i;
            next = n;
        }
    }
    public int size;
    public Node sentinel;
    public LinkedListDeque(){
        size = 0;
        sentinel = new Node(null, null, null);
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

    public void printDeque(){
        Node n = sentinel.next;
        while (n != sentinel){
            System.out.print(n.item);
            n = n.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        Node a = sentinel.next;
        sentinel.next = a.next;
        a.next.prev = sentinel;
        if (a != sentinel){
            size -= 1;
            return a.item;
        } else {
            return null;
        }
    }

    public T removeLast(){
        Node a = sentinel.prev;
        sentinel.prev = a.prev;
        a.prev.next = sentinel;
        if (a != sentinel){
            size -= 1;
            return a.item;
        } else {
            return null;
        }
    }

    public T get(int index){
        Node curr = sentinel.next;
        int curr_index = 0;
        while (curr != sentinel){
            if (curr_index != index){
                curr = curr.next;
                curr_index += 1;
            } else {
                return curr.item;
            }
        }
        return null;
    }

    public T getRecursive(int index){
        return getRecursiveHelper(index, sentinel.next);
    }

    public T getRecursiveHelper(int index, Node curr){
        if (curr == sentinel){
            return null;
        } else if (index == 0){
            return curr.item;
        } else {
            return getRecursiveHelper(index-1, curr.next);
        }
    }

    public boolean equals(Object o){
        if (o instanceof LinkedListDeque){
            for (int i = 0; i < size; i += 1){
                if (o.get(i) != this.get(i)){
                    return false;
                } else if (o.size() != size){
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            return false;
        }
    }



}
