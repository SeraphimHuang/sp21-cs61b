package deque;

public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    /**
     * Invariants:
     * 1. nextFirst != nextLast, if so, the array needs to be resized;
     */
    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    // resize to be done
    public void addFirst(T item){
        items[nextFirst] = item;
        size += 1;
        nextFirst = minusOne(nextFirst);
    }

    // resize to be done
    public void addLast(T item){
        items[nextLast] = item;
        size += 1;
        nextLast = plusOne(nextLast);
    }


    private int plusOne(int index){
        if (index < items.length - 1){
            return index + 1;
        }
        return 0;
    }

    private int minusOne(int index){
        if (index != 0){
            return index - 1;
        }
        return items.length - 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        // deque items[start, end)
        int start = plusOne(nextFirst);
        int end = nextLast;

        int index = start;
        while (index != end){
            System.out.print(items[index] + " ");
            index = plusOne(index);
        }
        System.out.println();
    }



}
