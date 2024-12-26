package deque;

public class ArrayDeque<T> {

    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    private static final int INIT_CAPACITY = 8;

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[INIT_CAPACITY];
        nextFirst = 0;
        nextLast = 1;
    }

    private int minusOne(int i) {
        return (i - 1 + items.length) % items.length;
    }

    private int plusOne(int i) {
        return (i + 1) % items.length;
    }

    public void addFirst(T item) {
        // if (size == items.length)
        items[nextFirst] = item;
        size += 1;
        nextFirst = minusOne(nextFirst);
    }

    public void addLast(T item) {
        // if (size == items.length)
        items[nextLast] = item;
        size += 1;
        nextLast = plusOne(nextLast);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = plusOne(nextFirst);

        for (int index = 0; index < size; index++) {
            System.out.print(items[i] + " ");
            i = plusOne(i);
        }
        System.out.println();
    }


    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = plusOne(nextFirst);
        size -= 1;
        return items[nextFirst];
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        nextLast = minusOne(nextLast);
        size -= 1;
        return items[nextLast];
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }

        index = (plusOne(nextFirst) + index) % items.length;
        return items[index];
    }
}
