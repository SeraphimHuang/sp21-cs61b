package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    private static final int INIT_CAPACITY = 8;
    private static final int RFACTOR = 2;
    private static final double MIN_USAGE_RATIO = 0.25;

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {

        private int currentIndex;
        private int count; // # elements traversed
        private int sizeIterator; // the size of the deque when the iterator is created

        /* the constructor is called when the iterator is created
        can exist multiple iterators
         */
        ArrayDequeIterator() {
            currentIndex = plusOne(nextFirst);
            count = 0;
            sizeIterator = size;
        }

        @Override
        public boolean hasNext() {
            if (count < sizeIterator) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T temp = items[currentIndex];
            count += 1;
            currentIndex = plusOne(currentIndex);
            return temp;
        }
    }

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

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }

        items[nextFirst] = item;
        size += 1;
        nextFirst = minusOne(nextFirst);
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }

        items[nextLast] = item;
        size += 1;
        nextLast = plusOne(nextLast);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int i = plusOne(nextFirst);

        for (int index = 0; index < size; index++) {
            System.out.print(items[i] + " ");
            i = plusOne(i);
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        if (items.length >= 16 && size - 1 < size * MIN_USAGE_RATIO) {
            resize(items.length / 2);
        }

        nextFirst = plusOne(nextFirst);
        size -= 1;
        return items[nextFirst];
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        if (items.length >= 16 && size - 1 < size * MIN_USAGE_RATIO) {
            resize(items.length / 2);
        }

        nextLast = minusOne(nextLast);
        size -= 1;
        return items[nextLast];
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }

        index = (plusOne(nextFirst) + index) % items.length;
        return items[index];
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<?> other = (Deque<?>) o;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }
}
