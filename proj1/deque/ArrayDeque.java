package deque;

public class ArrayDeque<T> {

    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    private static final int INIT_CAPACITY = 8;
    private static final int RFACTOR = 2;
    private static final double MIN_USAGE_ratio = 0.25;

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

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];

        int cur = plusOne(nextFirst);

        for (int i = 0; i < size; i++) {
            newItems[i] = items[cur];
            cur = plusOne(cur);
        }

        items = newItems;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }

        items[nextFirst] = item;
        size += 1;
        nextFirst = minusOne(nextFirst);
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }

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

        if (items.length >= 16 && size - 1 < size * MIN_USAGE_ratio) {
            resize(items.length / 2);
        }

        nextFirst = plusOne(nextFirst);
        size -= 1;
        return items[nextFirst];
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        if (items.length >= 16 && size - 1 < size * MIN_USAGE_ratio) {
            resize(items.length / 2);
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
